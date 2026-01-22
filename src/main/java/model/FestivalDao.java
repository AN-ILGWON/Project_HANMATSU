package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FestivalDao {
	
	public List<FestivalDto> getFestivalsByPage(int page, int pageSize, String status, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FestivalDto> list = new ArrayList<>();
		
		String sql = "SELECT * FROM ( " +
		             "  SELECT ROWNUM AS rn, a.* FROM ( " +
		             "    SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended " +
		             "    FROM hm_festival WHERE 1=1 ";
		
		if ("ongoing".equals(status)) {
			sql += " AND start_date <= TRUNC(SYSDATE) AND end_date >= TRUNC(SYSDATE) ";
		} else if ("upcoming".equals(status)) {
			sql += " AND start_date > TRUNC(SYSDATE) ";
		} else if ("past".equals(status)) {
			sql += " AND end_date < TRUNC(SYSDATE) ";
		} else if ("this_month".equals(status)) {
			sql += " AND start_date <= LAST_DAY(SYSDATE) AND end_date >= TRUNC(SYSDATE, 'MM') ";
		}
		
		if (keyword != null && !keyword.trim().isEmpty()) {
			sql += " AND (name LIKE ? OR description LIKE ? OR region LIKE ? OR location LIKE ?) ";
		}
		
		sql += " ORDER BY start_date ASC, end_date ASC " +
		       "  ) a WHERE ROWNUM <= ? " +
		       ") WHERE rn > ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKeyword = "%" + keyword.trim() + "%";
				pstmt.setString(idx++, searchKeyword);
				pstmt.setString(idx++, searchKeyword);
				pstmt.setString(idx++, searchKeyword);
				pstmt.setString(idx++, searchKeyword);
			}
			
			pstmt.setInt(idx++, page * pageSize);
			pstmt.setInt(idx++, (page - 1) * pageSize);
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int getFestivalCount(String status) {
		return getFestivalCount(status, null);
	}

	public int getFestivalCount(String status, String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String sql = "SELECT COUNT(*) FROM hm_festival WHERE 1=1 ";
		if ("ongoing".equals(status)) {
			sql += " AND start_date <= TRUNC(SYSDATE) AND end_date >= TRUNC(SYSDATE)";
		} else if ("upcoming".equals(status)) {
			sql += " AND start_date > TRUNC(SYSDATE)";
		} else if ("past".equals(status)) {
			sql += " AND end_date < TRUNC(SYSDATE)";
		} else if ("this_month".equals(status)) {
			sql += " AND start_date <= LAST_DAY(SYSDATE) AND end_date >= TRUNC(SYSDATE, 'MM')";
		}
		
		if (keyword != null && !keyword.trim().isEmpty()) {
			sql += " AND (name LIKE ? OR description LIKE ? OR region LIKE ? OR location LIKE ?) ";
		}
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			if (keyword != null && !keyword.trim().isEmpty()) {
				String searchKeyword = "%" + keyword.trim() + "%";
				pstmt.setString(1, searchKeyword);
				pstmt.setString(2, searchKeyword);
				pstmt.setString(3, searchKeyword);
				pstmt.setString(4, searchKeyword);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	// 今月の祭りリストを取得
	public List<FestivalDto> getThisMonthFestivals() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FestivalDto> list = new ArrayList<>();
		
		try {
			conn = DBManager.getInstance();
			// 2026년 1월 기준으로 데이터를 가져오도록 쿼리 수정 (또는 전체 데이터)
			String sql = "SELECT * FROM ( " +
			             "  SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended FROM hm_festival " +
			             "  WHERE end_date >= TO_DATE('2026-01-01', 'YYYY-MM-DD') " +
			             "  ORDER BY start_date ASC " +
			             ") WHERE ROWNUM <= 10";
			
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 올해의 축제 리스트를 가져옴
	public List<FestivalDto> getThisYearFestivals() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FestivalDto> list = new ArrayList<>();
		
		String sql = "SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended FROM hm_festival WHERE start_date >= TO_DATE('2026-01-01', 'YYYY-MM-DD') AND start_date <= TO_DATE('2026-12-31', 'YYYY-MM-DD') ORDER BY start_date ASC";
		
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 管理者が選択した推奨祭りを取得
	public FestivalDto getRecommendedFestival() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FestivalDto dto = null;
		
		// is_recommended가 'Y'인 축제를 가져옴 (없으면 최신 축제 하나)
		String sql = "SELECT * FROM ( " +
		             "  SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended " +
		             "  FROM hm_festival " +
		             "  ORDER BY is_recommended DESC, regdate DESC " +
		             ") WHERE ROWNUM = 1";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new FestivalDto();
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}

	// 推奨祭りを設定
	public void setRecommendedFestival(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getInstance();
			conn.setAutoCommit(false);
			
			// すべての推奨フラグをリセット
			String sql1 = "UPDATE hm_festival SET is_recommended = 'N'";
			pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			pstmt.close();
			
			// 指定した祭りを推奨に設定
			String sql2 = "UPDATE hm_festival SET is_recommended = 'Y' WHERE fno = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, fno);
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			try { if(conn != null) conn.rollback(); } catch(Exception ex) {}
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 모든 축제를 가져옴 (캘린더용)
	public List<FestivalDto> getAllFestivals() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FestivalDto> list = new ArrayList<>();
		
		String sql = "SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended FROM hm_festival ORDER BY start_date ASC";
		
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public FestivalDto getFestivalByFno(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT fno, region, name, description, TO_CHAR(start_date, 'YYYY-MM-DD') as start_date, TO_CHAR(end_date, 'YYYY-MM-DD') as end_date, location, imgfile, views, TO_CHAR(regdate, 'YYYY-MM-DD') as regdate, homepage, instagram, map_url, likes, is_recommended FROM hm_festival WHERE fno = ?";
		
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
				dto.setInstagram(rs.getString("instagram"));
				dto.setMapUrl(rs.getString("map_url"));
				dto.setLikes(rs.getInt("likes"));
				dto.setIsRecommended(rs.getString("is_recommended"));
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
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
			DBManager.close(conn, pstmt);
		}
	}
	// 祭りの情報を削除
	public void festivalDelete(int fno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM hm_festival WHERE fno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 祭りの情報を修正
	public void festivalUpdate(FestivalDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE hm_festival SET region = ?, name = ?, description = ?, start_date = TO_DATE(?, 'YYYY-MM-DD'), end_date = TO_DATE(?, 'YYYY-MM-DD'), location = ?, imgfile = ?, homepage = ?, instagram = ?, map_url = ?, likes = ? WHERE fno = ?";
		
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
			pstmt.setString(9, dto.getInstagram());
			pstmt.setString(10, dto.getMapUrl());
			pstmt.setInt(11, dto.getLikes());
			pstmt.setInt(12, dto.getFno());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 祭りの情報を登録
	public int festivalInsert(FestivalDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, regdate, homepage, instagram, map_url, likes) " +
					"VALUES (hm_festival_seq.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, 0, SYSDATE, ?, ?, ?, ?)";
		
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
			
			// 日付가 空の場合はNULLとして扱う
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
			pstmt.setString(9, dto.getInstagram());
			pstmt.setString(10, dto.getMapUrl());
			pstmt.setInt(11, dto.getLikes());
			
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
			DBManager.close(conn, pstmt);
		}
		return result;
	}
}

