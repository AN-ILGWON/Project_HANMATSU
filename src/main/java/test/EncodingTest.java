package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBManager;

public class EncodingTest {
    public static void main(String[] args) {
        Connection conn = DBManager.getInstance();
        try {
            String testStr = "【速報】鎮海の桜が満開です！🌸";
            System.out.println("Original: " + testStr);
            
            // Clean test table or just use a dummy query
            PreparedStatement pstmt = conn.prepareStatement("SELECT ? as val FROM dual");
            pstmt.setString(1, testStr);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String returned = rs.getString("val");
                System.out.println("Returned from DB: " + returned);
                if (testStr.equals(returned)) {
                    System.out.println("Encoding Match Success!");
                } else {
                    System.out.println("Encoding Match Failed!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null);
        }
    }
}
