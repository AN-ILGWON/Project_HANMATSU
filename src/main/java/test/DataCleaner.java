package test;
import java.sql.*;
import util.DBManager;

public class DataCleaner {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM hm_reply");
                stmt.executeUpdate("DELETE FROM hm_board_like");
                stmt.executeUpdate("DELETE FROM hm_wishlist");
                stmt.executeUpdate("DELETE FROM hm_visited");
                stmt.executeUpdate("DELETE FROM hm_board");
                stmt.executeUpdate("DELETE FROM hm_festival");
                stmt.executeUpdate("DELETE FROM hm_news");
                stmt.executeUpdate("DELETE FROM hm_banner");
                stmt.executeUpdate("DELETE FROM hm_site_info");
                stmt.executeUpdate("DELETE FROM hm_category");
                stmt.executeUpdate("DELETE FROM hm_member");
                System.out.println("All relevant tables cleared successfully!");
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
