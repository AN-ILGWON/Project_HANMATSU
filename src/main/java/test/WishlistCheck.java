package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBManager;

public class WishlistCheck {
    public static void main(String[] args) {
        Connection conn = DBManager.getInstance();
        if (conn == null) {
            System.err.println("Connection failed!");
            return;
        }

        try {
            String sql = "SELECT w.wno, w.userid, f.name FROM hm_wishlist w JOIN hm_festival f ON w.fno = f.fno";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("wno: " + rs.getInt("wno") + ", userid: " + rs.getString("userid") + ", festival: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null);
        }
    }
}
