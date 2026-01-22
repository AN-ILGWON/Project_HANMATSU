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

    // お気に入り追加
    public int insertWish(String userid, int fno) {
        String sql = "INSERT INTO hm_wishlist (wno, userid, fno, wdate) VALUES (hm_wishlist_seq.NEXTVAL, ?, ?, SYSDATE)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getInstance();
            if (conn == null) return 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("[WishlistDao] Error inserting wish: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return 0;
    }

    // お気に入り削除
    public int deleteWish(String userid, int fno) {
        String sql = "DELETE FROM hm_wishlist WHERE userid = ? AND fno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getInstance();
            if (conn == null) return 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("[WishlistDao] Error deleting wish: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt);
        }
        return 0;
    }

    // お気に入り確認
    public boolean isWished(String userid, int fno) {
        String sql = "SELECT COUNT(*) FROM hm_wishlist WHERE userid = ? AND fno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBManager.getInstance();
            if (conn == null) return false;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setInt(2, fno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            System.err.println("[WishlistDao] Error checking isWished: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return false;
    }

    // 会員のお気に入りリストを取得
    public List<FestivalDto> getWishList(String userid) {
        List<FestivalDto> list = new ArrayList<>();
        String sql = "SELECT f.fno, f.region, f.name, f.description, " +
                     "TO_CHAR(f.start_date, 'YYYY-MM-DD') as start_date, " +
                     "TO_CHAR(f.end_date, 'YYYY-MM-DD') as end_date, " +
                     "f.location, f.imgfile, f.views, TO_CHAR(f.regdate, 'YYYY-MM-DD') as regdate, " +
                     "f.homepage, f.instagram, f.map_url, f.likes " +
                     "FROM hm_festival f " +
                     "JOIN hm_wishlist w ON f.fno = w.fno " +
                     "WHERE w.userid = ? " +
                     "ORDER BY w.wdate DESC, w.wno DESC";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBManager.getInstance();
            if (conn == null) return list;
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
                dto.setInstagram(rs.getString("instagram"));
                dto.setMapUrl(rs.getString("map_url"));
                dto.setLikes(rs.getInt("likes"));
                list.add(dto);
            }
        } catch (Exception e) {
            System.err.println("[WishlistDao] Error getting wishlist for user " + userid + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            DBManager.close(conn, pstmt, rs);
        }
        return list;
    }
}
