package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newsTableCreate.do")
public class NewsTableCreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        
        String sql = "CREATE TABLE hm_news (" +
                     "    nno NUMBER PRIMARY KEY, " +
                     "    title VARCHAR2(300) NOT NULL, " +
                     "    category VARCHAR2(100), " +
                     "    regdate DATE DEFAULT SYSDATE, " +
                     "    imgfile VARCHAR2(500), " +
                     "    link_url VARCHAR2(500) " +
                     ")";
        String seqSql = "CREATE SEQUENCE hm_news_seq START WITH 1 INCREMENT BY 1";
        
        try (Connection conn = DBManager.getInstance();
             Statement stmt = conn.createStatement()) {
            try {
                stmt.executeUpdate(sql);
                response.getWriter().println("Table hm_news created.");
            } catch (Exception e) {
                response.getWriter().println("Table error: " + e.getMessage());
            }
            
            try {
                stmt.executeUpdate(seqSql);
                response.getWriter().println("Sequence hm_news_seq created.");
            } catch (Exception e) {
                response.getWriter().println("Sequence error: " + e.getMessage());
            }

            // 初期データの挿入
            stmt.executeUpdate("INSERT INTO hm_news (nno, title, category, regdate, imgfile) " +
                               "VALUES (hm_news_seq.NEXTVAL, '「ポケモンGo」平昌マスフェスティバルシーンイベント誘致', '江原観光財団', '2026-01-06', '')");
            stmt.executeUpdate("INSERT INTO hm_news (nno, title, category, regdate, imgfile) " +
                               "VALUES (hm_news_seq.NEXTVAL, '1月のおすすめ観光地（太白、紅川）', '江原観光の年', '2026-01-06', '')");
            stmt.executeUpdate("INSERT INTO hm_news (nno, title, category, regdate, imgfile) " +
                               "VALUES (hm_news_seq.NEXTVAL, '毎週土曜日、国楽にもっと楽しく深く会いましょう！', '国立国楽院', '2026-01-06', '')");
            response.getWriter().println("Initial news data inserted.");
            
        } catch (Exception e) {
            response.getWriter().println("DB Error: " + e.getMessage());
        }
    }
}
