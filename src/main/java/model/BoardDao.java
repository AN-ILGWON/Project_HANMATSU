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
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return list;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, offset + pageSize);
				pstmt.setInt(2, offset);
				try (ResultSet rs = pstmt.executeQuery()) {
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
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// いいねが多い投稿を取得 (上位N件)
	public List<BoardDto> getTopLikedBoards(int limit) {
		String sql = "SELECT * FROM ( " +
					"    SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as username, " +
					"    (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					"    FROM hm_board b " +
					"    LEFT JOIN hm_member m ON b.userid = m.userid " +
					"    ORDER BY b.likes DESC, b.bno DESC) " +
					"WHERE ROWNUM <= ?";
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return list;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, limit);
				try (ResultSet rs = pstmt.executeQuery()) {
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
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// カテゴリ別投稿一覧を取得 (ページング)
	public List<BoardDto> getBoardListByCategory(String category, int page, int pageSize) {
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
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return list;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, category);
				pstmt.setInt(2, offset + pageSize);
				pstmt.setInt(3, offset);
				try (ResultSet rs = pstmt.executeQuery()) {
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
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 投稿詳細を取得
	public BoardDto getBoardByBno(int bno) {
		String sql = "SELECT b.*, CASE WHEN b.userid = 'superadmin' THEN '運営事務局' ELSE m.nickname END as nickname, " +
					" (SELECT COUNT(*) FROM hm_reply r WHERE r.bno = b.bno) as replyCount " +
					" FROM hm_board b " +
					" LEFT JOIN hm_member m ON b.userid = m.userid " +
					" WHERE b.bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return null;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				try (ResultSet rs = pstmt.executeQuery()) {
					if(rs.next()) {
						BoardDto dto = new BoardDto();
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 投稿作成
	public void boardInsert(BoardDto dto) {
		String sql = "INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, imgfile, category) " +
					"VALUES (hm_board_seq.NEXTVAL, ?, ?, ?, 0, 0, SYSDATE, ?, ?)";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, dto.getUserid());
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getContent());
				pstmt.setString(4, dto.getImgfile());
				pstmt.setString(5, dto.getCategory());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 投稿編集
	public void boardUpdate(BoardDto dto) {
		String sql = "UPDATE hm_board SET title = ?, content = ?, imgfile = ?, category = ? WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getImgfile());
				pstmt.setString(4, dto.getCategory());
				pstmt.setInt(5, dto.getBno());
				
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 投稿削除
	public int boardDelete(int bno) {
		String sql = "DELETE FROM hm_board WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return 0;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				return pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 全投稿数を取得
	public int getBoardCount() {
		String sql = "SELECT COUNT(*) FROM hm_board";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return 0;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = pstmt.executeQuery()) {
					if(rs.next()) {
						return rs.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// カテゴリ別投稿数を取得
	public int getBoardCountByCategory(String category) {
		String sql = "SELECT COUNT(*) FROM hm_board WHERE category = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return 0;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, category);
				try (ResultSet rs = pstmt.executeQuery()) {
					if(rs.next()) {
						return rs.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// 閲覧数を増やす
	public void viewCount(int bno) {
		String sql = "UPDATE hm_board SET views = views + 1 WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 投稿者の確認
	public String getWriterId(int bno) {
		String sql = "SELECT userid FROM hm_board WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return null;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
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

	// いいね数を修正 (管理者用)
	public void updateLikeCount(int bno, int likes) {
		String sql = "UPDATE hm_board SET likes = ? WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, likes);
				pstmt.setInt(2, bno);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

