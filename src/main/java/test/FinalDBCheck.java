package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBManager;

public class FinalDBCheck {
    public static void main(String[] args) {
        Connection conn = DBManager.getInstance();
        try (Statement stmt = conn.createStatement()) {
            System.out.println("--- Festival Data ---");
            ResultSet rs = stmt.executeQuery("SELECT fno, name, imgfile FROM hm_festival");
            while (rs.next()) {
                System.out.println("fno: " + rs.getInt("fno") + ", name: " + rs.getString("name") + ", imgfile: " + rs.getString("imgfile"));
            }

            System.out.println("\n--- Board Data ---");
            rs = stmt.executeQuery("SELECT bno, userid, title, imgfile FROM hm_board");
            while (rs.next()) {
                System.out.println("bno: " + rs.getInt("bno") + ", user: " + rs.getString("userid") + ", title: " + rs.getString("title") + ", img: " + rs.getString("imgfile"));
            }

            System.out.println("\n--- Wishlist Data ---");
            rs = stmt.executeQuery("SELECT w.wno, w.userid, f.name FROM hm_wishlist w JOIN hm_festival f ON w.fno = f.fno");
            while (rs.next()) {
                System.out.println("wno: " + rs.getInt("wno") + ", user: " + rs.getString("userid") + ", festival: " + rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(conn, null);
        }
    }
}
