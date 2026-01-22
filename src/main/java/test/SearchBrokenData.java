package test;
import java.sql.*;
import util.DBManager;

public class SearchBrokenData {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            String[] tables = {"hm_festival", "hm_board", "hm_banner", "hm_news"};
            for (String table : tables) {
                System.out.println("Searching in " + table + "...");
                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
                    ResultSetMetaData meta = rs.getMetaData();
                    int cols = meta.getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= cols; i++) {
                            String val = rs.getString(i);
                            if (val != null && val.contains("94f2aafa")) {
                                System.out.println("Found in " + table + " column " + meta.getColumnName(i) + ": " + val);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
