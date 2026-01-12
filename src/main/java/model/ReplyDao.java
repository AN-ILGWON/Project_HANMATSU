package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ReplyDao {
	
	// コメント一覧を取得
	public List<ReplyDto> getReplyList(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT r.*, CASE WHEN r.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username FROM hm_reply r " +
					"LEFT JOIN hm_member m ON r.userid = m.userid " +
					"WHERE r.bno = ? ORDER BY r.rno DESC";
		
		List<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto();
				dto.setRno(rs.getInt("rno"));
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	public List<ReplyDto> getReplyListByUser(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT r.*, CASE WHEN r.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, b.title as board_title FROM hm_reply r " +
					"LEFT JOIN hm_member m ON r.userid = m.userid " +
					"LEFT JOIN hm_board b ON r.bno = b.bno " +
					"WHERE r.userid = ? ORDER BY r.rno DESC";
		
		List<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReplyDto dto = new ReplyDto();
				dto.setRno(rs.getInt("rno"));
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				// Board title is useful for MyPage
				dto.setBoardTitle(rs.getString("board_title"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	// コメント投稿
	public void replyInsert(ReplyDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO hm_reply (rno, bno, userid, content, regdate) " +
					"VALUES (hm_reply_seq.NEXTVAL, ?, ?, ?, DEFAULT)";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBno());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// コメント編集
	public void replyUpdate(ReplyDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_reply SET content = ? WHERE rno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getRno());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// コメント削除
	public void replyDelete(int rno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE hm_reply WHERE rno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// コメント投稿者の確認
	public String getWriterId(int rno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT userid FROM hm_reply WHERE rno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("userid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}

