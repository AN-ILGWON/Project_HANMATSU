package test;

import java.sql.*;
import util.DBManager;

public class SimpleDataCheck {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBManager.getInstance();
            System.out.println("Connection OK!");
            
            Statement stmt = conn.createStatement();
            
            System.out.println("\n--- Festivals Count ---");
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM hm_festival");
            if(rs.next()) System.out.println("Total Festivals: " + rs.getInt(1));
            
            System.out.println("\n--- Recommended Festivals ---");
            rs = stmt.executeQuery("SELECT name, is_recommended FROM hm_festival WHERE is_recommended = 'Y'");
            while(rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2));
            }
            
            System.out.println("\n--- Sequences ---");
            rs = stmt.executeQuery("SELECT sequence_name FROM user_sequences");
            while(rs.next()) {
                System.out.println("Seq: " + rs.getString(1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null, null);
        }
    }
}
