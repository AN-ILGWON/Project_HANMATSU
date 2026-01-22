package test;
import java.sql.*;
import util.DBManager;

public class ExportLatestData {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            System.out.println("-- HM_NEWS LATEST DATA --");
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM hm_news ORDER BY regdate DESC");
                while (rs.next()) {
                    System.out.printf("INSERT INTO hm_news (nno, title, category, content, imgfile, link_url, regdate) VALUES (hm_news_seq.nextval, '%s', '%s', '%s', '%s', '%s', CURRENT_TIMESTAMP);%n",
                        rs.getString("title"), rs.getString("category"), rs.getString("content"), rs.getString("imgfile"), rs.getString("link_url"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
