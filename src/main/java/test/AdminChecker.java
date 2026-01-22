package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBManager;

public class AdminChecker {
    public static void main(String[] args) {
        String sql = "SELECT userid, nickname, role FROM hm_member WHERE role = 'ADMIN'";
        
        try (Connection conn = DBManager.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("--- Administrator Account Status ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getString("userid"));
                System.out.println("Nickname: " + rs.getString("nickname"));
                System.out.println("Role: " + rs.getString("role"));
                System.out.println("----------------------------------");
            }
            
            if (!found) {
                System.out.println("No administrator account found!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
