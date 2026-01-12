package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FestivalDao {
	
	public List<FestivalDto> getFestivalsByPage(int page, int pageSize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FestivalDto> list = new ArrayList<>();
		
		String sql = "SELECT * FROM ( " +
		             "  SELECT ROWNUM AS rn, a.* FROM ( " +
		             "    SELECT * FROM hm_festival ORDER BY start_date ASC " +
		             "  ) a WHERE ROWNUM <= ? " +
		             ") WHERE rn > ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page * pageSize);
			pstmt.setInt(2, (page - 1) * pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FestivalDto dto = new FestivalDto();
				dto.setFno(rs.getInt("fno"));
				dto.setRegion(rs.getString("region"));
				dto.setName(rs.getString("name"));
				dto.setDescription(rs.getString("description"));
				dto.setStartDate(rs.getString("start_date"));
				dto.setEndDate(rs.getString("end_date"));
				dto.setLocation(rs.getString("location"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setViews(rs.getInt("views"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHomepage(rs.getString("homepage"));
				dto.setMapUrl(rs.getString("map_url"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int getFestivalCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String sql = "SELECT COUNT(*) FROM hm_festival";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	// 今年の祭りリストを取得 (現在の年基準で取得)
	public List<FestivalDto> getThisYearFestivals() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 現在の年以降の祭りを取得 (より多くのデータを見せるため)
		String sql = "SELECT * FROM hm_festival WHERE start_date >= TRUNC(SYSDATE, 'YYYY') ORDER BY start_date ASC";
		
		// もし現在の年のデータがない場合は、全てのデータを取得するようにフォールバック
		List<FestivalDto> list = new ArrayList<FestivalDto>();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FestivalDto dto = new FestivalDto();
				dto.setFno(rs.getInt("fno"));
				dto.setRegion(rs.getString("region"));
				dto.setName(rs.getString("name"));
				dto.setDescription(rs.getString("description"));
				dto.setStartDate(rs.getString("start_date"));
				dto.setEndDate(rs.getString("end_date"));
				dto.setLocation(rs.getString("location"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setViews(rs.getInt("views"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHomepage(rs.getString("homepage"));
				dto.setMapUrl(rs.getString("map_url"));
				
				list.add(dto);
			}
			
			// 現在の年のデータがない場合、全データを取得 (最新順)
			if (list.isEmpty()) {
				sql = "SELECT * FROM hm_festival ORDER BY start_date ASC";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					FestivalDto dto = new FestivalDto();
					dto.setFno(rs.getInt("fno"));
					dto.setRegion(rs.getString("region"));
					dto.setName(rs.getString("name"));
					dto.setDescription(rs.getString("description"));
					dto.setStartDate(rs.getString("start_date"));
					dto.setEndDate(rs.getString("end_date"));
					dto.setLocation(rs.getString("location"));
					dto.setImgfile(rs.getString("imgfile"));
					dto.setViews(rs.getInt("views"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setHomepage(rs.getString("homepage"));
					dto.setMapUrl(rs.getString("map_url"));
					list.add(dto);
				}
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
		return list; // nullの代わりに空のリストを返す
	}
	
	// お祭りの詳細情報を取得
	public FestivalDto getFestivalByFno(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hm_festival WHERE fno = ?";
		
		FestivalDto dto = new FestivalDto();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setFno(rs.getInt("fno"));
				dto.setRegion(rs.getString("region"));
				dto.setName(rs.getString("name"));
				dto.setDescription(rs.getString("description"));
				dto.setStartDate(rs.getString("start_date"));
				dto.setEndDate(rs.getString("end_date"));
				dto.setLocation(rs.getString("location"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setViews(rs.getInt("views"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setHomepage(rs.getString("homepage"));
				dto.setMapUrl(rs.getString("map_url"));
			}
			return dto;
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
	
	// 閲覧数を増やす
	public void viewCount(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_festival SET views = views + 1 WHERE fno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
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
	// 祭りの情報を削除
	public void festivalDelete(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE hm_festival WHERE fno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
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

	// 祭りの情報を修正
	public void festivalUpdate(FestivalDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_festival SET region = ?, name = ?, description = ?, start_date = TO_DATE(?, 'YYYY-MM-DD'), end_date = TO_DATE(?, 'YYYY-MM-DD'), location = ?, imgfile = ?, homepage = ?, map_url = ? WHERE fno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRegion());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getStartDate());
			pstmt.setString(5, dto.getEndDate());
			pstmt.setString(6, dto.getLocation());
			pstmt.setString(7, dto.getImgfile());
			pstmt.setString(8, dto.getHomepage());
			pstmt.setString(9, dto.getMapUrl());
			pstmt.setInt(10, dto.getFno());
			
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

	// 祭りの情報を登録
	public int festivalInsert(FestivalDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		// hm_festival_seq.NEXTVAL が存在しない場合のエラーを防ぐため、ログを強化
		String sql = "INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, regdate, homepage, map_url) " +
					"VALUES (hm_festival_seq.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, 0, SYSDATE, ?, ?)";
		
		try {
			conn = DBManager.getInstance();
			if (conn == null) {
				System.out.println("[ERROR] festivalInsert: Database connection is null!");
				return 0;
			}
			
			System.out.println("[INFO] festivalInsert: Attempting to insert festival - Name: " + dto.getName() + ", StartDate: " + dto.getStartDate());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRegion());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDescription());
			
			// 日付が空の場合はNULLとして扱う
			if (dto.getStartDate() == null || dto.getStartDate().trim().isEmpty()) {
				pstmt.setNull(4, java.sql.Types.VARCHAR);
			} else {
				pstmt.setString(4, dto.getStartDate());
			}
			
			if (dto.getEndDate() == null || dto.getEndDate().trim().isEmpty()) {
				pstmt.setNull(5, java.sql.Types.VARCHAR);
			} else {
				pstmt.setString(5, dto.getEndDate());
			}
			
			pstmt.setString(6, dto.getLocation());
			pstmt.setString(7, dto.getImgfile());
			pstmt.setString(8, dto.getHomepage());
			pstmt.setString(9, dto.getMapUrl());
			
			result = pstmt.executeUpdate();
			System.out.println("[INFO] festivalInsert: Success! Rows affected: " + result);
		} catch (java.sql.SQLException se) {
			System.out.println("[ERROR] festivalInsert SQL Error: " + se.getMessage());
			if (se.getMessage().contains("ORA-02289")) {
				System.out.println("[CRITICAL] hm_festival_seq sequence is missing in the database!");
			}
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("[ERROR] festivalInsert failed: " + e.getMessage());
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
}

