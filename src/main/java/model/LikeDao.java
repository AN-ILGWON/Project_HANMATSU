package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import util.DBManager;

public class LikeDao {
	
	// いいね追加
	public int likeInsert(int bno, String userid) {
		String sql = "INSERT INTO hm_board_like (like_no, bno, userid, regdate) " +
					"VALUES (hm_like_seq.NEXTVAL, ?, ?, SYSDATE)";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return 0;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				pstmt.setString(2, userid);
				
				int result = pstmt.executeUpdate();
				if(result > 0) {
					// 投稿のいいね数を増やす
					updateLikeCount(bno, 1);
				}
				return result;
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			// 既にいいねを押している場合
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// いいね削除
	public int likeDelete(int bno, String userid) {
		String sql = "DELETE FROM hm_board_like WHERE bno = ? AND userid = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return 0;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				pstmt.setString(2, userid);
				
				int result = pstmt.executeUpdate();
				if(result > 0) {
					// 投稿のいいね数を減らす
					updateLikeCount(bno, -1);
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// いいね済みか確認
	public boolean isLiked(int bno, String userid) {
		String sql = "SELECT * FROM hm_board_like WHERE bno = ? AND userid = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return false;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, bno);
				pstmt.setString(2, userid);
				try (ResultSet rs = pstmt.executeQuery()) {
					return rs.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 投稿テーブルのいいね数を更新
	private void updateLikeCount(int bno, int increment) {
		String sql = "UPDATE hm_board SET likes = likes + ? WHERE bno = ?";
		
		try (Connection conn = DBManager.getInstance()) {
			if (conn == null) return;
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, increment);
				pstmt.setInt(2, bno);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

