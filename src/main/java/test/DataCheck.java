package test;

import java.sql.*;
import util.DBManager;

public class DataCheck {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBManager.getInstance();
            if (conn == null) {
                System.out.println("Failed to connect to database.");
                return;
            }
            
            System.out.println("--- Banners ---");
            try {
                pstmt = conn.prepareStatement("SELECT bano, title, imgfile, is_active FROM hm_banner");
                rs = pstmt.executeQuery();
                while(rs.next()) {
                    System.out.println(rs.getInt("bano") + " | " + rs.getString("title") + " | " + rs.getString("imgfile") + " | " + rs.getString("is_active"));
                }
            } catch (SQLException e) {
                System.out.println("Error querying banners: " + e.getMessage());
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            }
            
            System.out.println("\n--- All Festivals ---");
            try {
                pstmt = conn.prepareStatement("SELECT fno, name, TO_CHAR(start_date, 'YYYY-MM-DD') as sd, TO_CHAR(end_date, 'YYYY-MM-DD') as ed, is_recommended FROM hm_festival ORDER BY start_date ASC");
                rs = pstmt.executeQuery();
                while(rs.next()) {
                    System.out.println(rs.getInt("fno") + " | " + rs.getString("name") + " | " + rs.getString("sd") + " | " + rs.getString("ed") + " | Recommended: " + rs.getString("is_recommended"));
                }
            } catch (SQLException e) {
                System.out.println("Error querying festivals: " + e.getMessage());
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            }

            System.out.println("\n--- Sequence Check ---");
            try {
                pstmt = conn.prepareStatement("SELECT sequence_name, last_number FROM user_sequences WHERE sequence_name = 'HM_FESTIVAL_SEQ'");
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    System.out.println("Sequence: " + rs.getString("sequence_name") + " | Last Number: " + rs.getLong("last_number"));
                } else {
                    System.out.println("CRITICAL: HM_FESTIVAL_SEQ NOT FOUND!");
                }
            } catch (SQLException e) {
                System.out.println("Error querying sequence: " + e.getMessage());
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            }

            System.out.println("\n--- Admin Users ---");
            try {
                pstmt = conn.prepareStatement("SELECT userid, nickname, role FROM hm_member WHERE role = 'ADMIN'");
                rs = pstmt.executeQuery();
                while(rs.next()) {
                    System.out.println("Admin: " + rs.getString("userid") + " (" + rs.getString("nickname") + ")");
                }
            } catch (SQLException e) {
                System.out.println("Error querying admins: " + e.getMessage());
            } finally {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null, null);
        }
    }
}
