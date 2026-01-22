package test;
import java.sql.*;
import util.DBManager;
import java.io.*;

public class CountData {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance();
             PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("data_counts.txt"), "UTF-8"))) {
            
            String[] tables = {"hm_festival", "hm_banner", "hm_member", "hm_board"};
            for (String table : tables) {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table)) {
                    if (rs.next()) {
                        out.println(table + ": " + rs.getInt(1));
                    }
                }
            }
            
            out.println("\n--- Festival List Details ---");
                    try (Statement stmt = conn.createStatement();
                         ResultSet rs = stmt.executeQuery("SELECT fno, name, TO_CHAR(start_date, 'YYYY-MM-DD') as sd, TO_CHAR(end_date, 'YYYY-MM-DD') as ed, is_recommended FROM hm_festival ORDER BY fno ASC")) {
                        while (rs.next()) {
                            out.println("fno: " + rs.getInt("fno") + " | name: " + rs.getString("name") + " | dates: " + rs.getString("sd") + " ~ " + rs.getString("ed") + " | recommended: " + rs.getString("is_recommended"));
                        }
                    }
            
            System.out.println("Counts written to data_counts.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
