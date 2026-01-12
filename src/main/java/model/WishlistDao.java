package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBManager;

public class WishlistDao {
    private static WishlistDao instance = new WishlistDao();
    private WishlistDao() {}
    public static WishlistDao getInstance() {
        return instance;
    }

    // 찜하기 추가
    public int insertWish(String userid, int fno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO hm_wishlist (wno, userid, fno) VALUES (hm_wishlist_seq.NEXTVAL, ?, ?)";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return 0;
    }

    // 찜하기 삭제
    public int deleteWish(String userid, int fno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM hm_wishlist WHERE userid = ? AND fno = ?";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return 0;
    }

    // 찜 확인
    public boolean isWished(String userid, int fno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM hm_wishlist WHERE userid = ? AND fno = ?";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return false;
    }

    // 회원의 찜 목록 가져오기
    public List<FestivalDto> getWishList(String userid) {
        List<FestivalDto> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT f.* FROM hm_festival f JOIN hm_wishlist w ON f.fno = w.fno WHERE w.userid = ? ORDER BY w.wdate DESC";
        try {
            conn = DBManager.getInstance();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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
}
