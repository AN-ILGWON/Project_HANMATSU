package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class MemberDao {
	
	// IDの使用状況チェック
	public int userIdcheck(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hm_member WHERE userid = ?";
		int result = 0;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
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
		return result;
	}
	
	// ニックネームの重複チェック
	public int nicknameCheck(String nickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hm_member WHERE nickname = ?";
		int result = 0;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
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
		return result;
	}

	// 会員登録保存メソッド
	public int memberSave(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
        String sql = "INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role, regdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
		
		int result = 0;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
            pstmt.setString(2, dto.getPassword());
            pstmt.setString(3, dto.getNickname());
            pstmt.setString(4, dto.getEmail());
            pstmt.setString(5, dto.getPhone());
            pstmt.setString(6, dto.getLastNameKanji());
            pstmt.setString(7, dto.getFirstNameKanji());
            pstmt.setString(8, dto.getLastNameKana());
            pstmt.setString(9, dto.getFirstNameKana());
			
			result = pstmt.executeUpdate();
			System.out.println("Member saved successfully: " + dto.getUserid());
		} catch (Exception e) {
			System.err.println("Error saving member: " + dto.getUserid());
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	// IDの存在確認
	public MemberDto searchUserId(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hm_member WHERE userid = ?";
		
		MemberDto dto = null;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDto();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setLastNameKanji(rs.getString("last_name_kanji"));
				dto.setFirstNameKanji(rs.getString("first_name_kanji"));
				dto.setLastNameKana(rs.getString("last_name_kana"));
				dto.setFirstNameKana(rs.getString("first_name_kana"));
				dto.setRole(rs.getString("role"));
				dto.setRegdate(rs.getString("regdate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
}

