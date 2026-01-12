package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BoardDao {
	
	// 投稿一覧を取得 (ページング)
	public List<BoardDto> getBoardList(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ( " +
					"    SELECT ROWNUM rn, aaa.* FROM ( " +
					"    SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, " +
					"    (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					"    FROM hm_board b " +
					"    LEFT JOIN hm_member m ON b.userid = m.userid " +
					"    ORDER BY b.bno DESC) aaa " +
					"    WHERE ROWNUM <= ?) " +
					"WHERE rn > ?";
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		int offset = (page - 1) * pageSize;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset + pageSize);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViews(rs.getInt("views"));
				dto.setLikes(rs.getInt("likes"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setReplyCount(rs.getInt("replyCount"));
				dto.setCategory(rs.getString("category"));
				
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
	
	// いいねが多い投稿を取得 (上位N件)
	public List<BoardDto> getTopLikedBoards(int limit) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ( " +
					"    SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, " +
					"    (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					"    FROM hm_board b " +
					"    LEFT JOIN hm_member m ON b.userid = m.userid " +
					"    ORDER BY b.likes DESC, b.bno DESC) " +
					"WHERE ROWNUM <= ?";
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViews(rs.getInt("views"));
				dto.setLikes(rs.getInt("likes"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setReplyCount(rs.getInt("replyCount"));
				dto.setCategory(rs.getString("category"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	// カテゴリ別投稿一覧を取得 (ページング)
	public List<BoardDto> getBoardListByCategory(String category, int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ( " +
					"    SELECT ROWNUM rn, aaa.* FROM ( " +
					"    SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, " +
					"    (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					"    FROM hm_board b " +
					"    LEFT JOIN hm_member m ON b.userid = m.userid " +
					"    WHERE b.category = ? " +
					"    ORDER BY b.bno DESC) aaa " +
					"    WHERE ROWNUM <= ?) " +
					"WHERE rn > ?";
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		int offset = (page - 1) * pageSize;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, offset + pageSize);
			pstmt.setInt(3, offset);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViews(rs.getInt("views"));
				dto.setLikes(rs.getInt("likes"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setReplyCount(rs.getInt("replyCount"));
				dto.setCategory(rs.getString("category"));
				
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	// 投稿詳細を取得
	public BoardDto getBoardByBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as nickname, " +
					" (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					" FROM hm_board b " +
					" LEFT JOIN hm_member m ON b.userid = m.userid " +
					" WHERE b.bno = ?";
		
		BoardDto dto = new BoardDto();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("nickname"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViews(rs.getInt("views"));
				dto.setLikes(rs.getInt("likes"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setReplyCount(rs.getInt("replyCount"));
				dto.setCategory(rs.getString("category"));
				
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	// 投稿作成
	public void boardInsert(BoardDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, imgfile, category) " +
					"VALUES (hm_board_seq.NEXTVAL, ?, ?, ?, 0, 0, DEFAULT, ?, ?)";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getImgfile());
			pstmt.setString(5, dto.getCategory());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 投稿編集
	public void boardUpdate(BoardDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_board SET title = ?, content = ?, imgfile = ?, category = ? WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImgfile());
			pstmt.setString(4, dto.getCategory());
			pstmt.setInt(5, dto.getBno());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 投稿削除
	public void boardDelete(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE hm_board WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 全投稿数を取得
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM hm_board";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}

	// カテゴリ別投稿数を取得
	public int getBoardCountByCategory(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM hm_board WHERE category = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
	// 閲覧数を増やす
	public void viewCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_board SET views = views + 1 WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
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
	
	// 投稿者の確認
	public String getWriterId(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT userid FROM hm_board WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
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

	// いいね数を修正 (管理者用)
	public void updateLikeCount(int bno, int likes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_board SET likes = ? WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likes);
			pstmt.setInt(2, bno);
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
}

