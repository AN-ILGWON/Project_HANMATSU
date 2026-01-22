package test;
import java.sql.*;
import java.io.File;
import util.DBManager;
import util.FileConfig;

public class ImageFileVerify {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            System.out.println("Checking banner images...");
            checkTable(conn, "SELECT imgfile FROM hm_banner");
            System.out.println("\nChecking board images...");
            checkTable(conn, "SELECT imgfile FROM hm_board");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkTable(Connection conn, String sql) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String imgFile = rs.getString("imgfile");
                if (imgFile == null) continue;
                File file = new File(FileConfig.UPLOAD_PATH, imgFile);
                System.out.print("File in DB: " + imgFile + " -> ");
                if (file.exists()) {
                    System.out.println("EXISTS! (o)");
                } else {
                    System.out.println("MISSING! (x) at " + file.getAbsolutePath());
                }
            }
        }
    }
}
