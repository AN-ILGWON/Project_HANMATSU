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
		String sql = "SELECT r.*, CASE WHEN r.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, m.profile_img FROM hm_reply r " +
					"LEFT JOIN hm_member m ON r.userid = m.userid " +
					"WHERE r.bno = ? ORDER BY r.rno DESC";
		
		List<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return list;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				try (ResultSet rs = pstmt.executeQuery()) {
					while(rs.next()) {
						ReplyDto dto = new ReplyDto();
						dto.setRno(rs.getInt("rno"));
						dto.setBno(rs.getInt("bno"));
						dto.setUserid(rs.getString("userid"));
						dto.setUsername(rs.getString("username"));
						dto.setContent(rs.getString("content"));
						dto.setRegdate(rs.getString("regdate"));
						dto.setProfileImg(rs.getString("profile_img"));
						
						list.add(dto);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ReplyDto> getReplyListByUser(String userid) {
		String sql = "SELECT r.*, CASE WHEN r.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, m.profile_img, b.title as board_title FROM hm_reply r " +
					"LEFT JOIN hm_member m ON r.userid = m.userid " +
					"LEFT JOIN hm_board b ON r.bno = b.bno " +
					"WHERE r.userid = ? ORDER BY r.rno DESC";
		
		List<ReplyDto> list = new ArrayList<ReplyDto>();
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return list;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userid);
				try (ResultSet rs = pstmt.executeQuery()) {
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
						dto.setProfileImg(rs.getString("profile_img"));
						
						list.add(dto);
					}
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// コメント投稿
	public void replyInsert(ReplyDto dto) {
		String sql = "INSERT INTO hm_reply (rno, bno, userid, content, regdate) " +
					"VALUES (hm_reply_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, dto.getBno());
				pstmt.setString(2, dto.getUserid());
				pstmt.setString(3, dto.getContent());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// コメント編集
	public void replyUpdate(ReplyDto dto) {
		String sql = "UPDATE hm_reply SET content = ? WHERE rno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, dto.getContent());
				pstmt.setInt(2, dto.getRno());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// コメント削除
	public void replyDelete(int rno) {
		String sql = "DELETE FROM hm_reply WHERE rno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, rno);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// コメント投稿者の確認
	public String getWriterId(int rno) {
		String sql = "SELECT userid FROM hm_reply WHERE rno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return null;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, rno);
				try (ResultSet rs = pstmt.executeQuery()) {
					if(rs.next()) {
						return rs.getString("userid");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

