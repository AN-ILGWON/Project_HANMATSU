package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import util.DBManager;

public class LikeDao {
	
	// いいね追加
	public int likeInsert(int bno, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO hm_board_like (like_no, bno, userid, regdate) " +
					"VALUES (hm_like_seq.NEXTVAL, ?, ?, DEFAULT)";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, userid);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				// 投稿のいいね数を増やす
				updateLikeCount(bno, 1);
			}
			return result;
		} catch(SQLIntegrityConstraintViolationException e) {
			// 既にいいねを押している場合
			return -1;
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
		return 0;
	}
	
	// いいね削除
	public int likeDelete(int bno, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE hm_board_like WHERE bno = ? AND userid = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, userid);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				// 投稿のいいね数を減らす
				updateLikeCount(bno, -1);
			}
			return result;
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
		return 0;
	}
	
	// いいね済みか確認
	public boolean isLiked(int bno, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hm_board_like WHERE bno = ? AND userid = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			
			return rs.next();
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
		return false;
	}
	
	// 投稿テーブルのいいね数を更新
	private void updateLikeCount(int bno, int increment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_board SET likes = likes + ? WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, increment);
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

