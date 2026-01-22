package test;
import java.sql.*;
import util.DBManager;

public class UserDataRestorer {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            conn.setAutoCommit(false);
            
            // 1. Restore Banners (Japanese)
            String bannerSql = "INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) VALUES (hm_banner_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(bannerSql)) {
                // Banner 1
                pstmt.setString(1, "🌸 桜色に染まる、夢の韓国旅へ 🌸");
                pstmt.setString(2, "36万本の桜가 織りなす、ロマンチックな春の散歩道。");
                pstmt.setString(3, "94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 1);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();
                
                // Banner 2
                pstmt.setString(1, "✨ きらめくお祭りの魔法にかかって ✨");
                pstmt.setString(2, "泥まみれになって笑い合えば、最高の夏の思い出に！");
                pstmt.setString(3, "2ee36fb2-08a1-4c8e-a315-f54b1355d12d_머드축제보령.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 2);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();
                
                // Banner 3
                pstmt.setString(1, "🔥 夜空を焦가す、神秘的な火の祭典 🔥");
                pstmt.setString(2, "燃え上がる山に願いを込めて、忘れられない感動を。");
                pstmt.setString(3, "7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 3);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();
            }

            // 2. Restore Community Posts (Board - Japanese)
            String boardSql = "INSERT INTO hm_board (bno, userid, category, title, content, imgfile, regdate, views, likes) VALUES (hm_board_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(boardSql)) {
                // Post 1
                pstmt.setString(1, "kawaii_matsuri");
                pstmt.setString(2, "祭りレビュー");
                pstmt.setString(3, "🌸 桜の精霊に会いに来ちゃった！鎮海の桜、満開だにゃん 🌸");
                pstmt.setString(4, "今朝、鎮海（チネ）に到着しました！ロマンス橋の桜がちょうど満開で、本当に天国のようです。写真をシェアしますね！");
                pstmt.setString(5, "94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg");
                pstmt.setInt(6, 150);
                pstmt.setInt(7, 45);
                pstmt.executeUpdate();
                
                // Post 2
                pstmt.setString(1, "beauty_otaku");
                pstmt.setString(2, "自由掲示板");
                pstmt.setString(3, "✨ オリーブヤングで「可愛さ」を爆買いしちゃったにゃん ✨");
                pstmt.setString(4, "最近の韓国旅行でゲットしたおすすめコスメをまとめました。祭りの強い日差しで疲れた肌に効くパック、最高です。");
                pstmt.setString(5, "148f7d6b-b780-4d11-bfd2-3803915159d3_올리브영.jpg");
                pstmt.setInt(6, 210);
                pstmt.setInt(7, 55);
                pstmt.executeUpdate();

                // Post 3
                pstmt.setString(1, "kawaii_matsuri");
                pstmt.setString(2, "祭りレビュー");
                pstmt.setString(3, "☕ 聖水のカフェで、ほっこり癒やしのティータイム ☕");
                pstmt.setString(4, "聖水（ソンス）エリアに新しくできたカフェに行ってきました。お祭りの喧騒を忘れて、ゆったりした時間を過ごせます。");
                pstmt.setString(5, "8348cc5c-12fe-483d-8b22-0397ebf016df_성수카페5.jpg");
                pstmt.setInt(6, 120);
                pstmt.setInt(7, 32);
                pstmt.executeUpdate();
            }

            // 3. Restore News (Japanese)
            String newsSql = "INSERT INTO hm_news (nno, category, title, content, imgfile, regdate) VALUES (hm_news_seq.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
            try (PreparedStatement pstmt = conn.prepareStatement(newsSql)) {
                pstmt.setString(1, "Trend");
                pstmt.setString(2, "2026年韓国旅行の最新トレンドは「地域密着型」");
                pstmt.setString(3, "2026年の韓国旅行は、有名な観光地だけでなく、その土地ならではの文化や祭りを体験する「地域密着型」が注目されています。");
                pstmt.setString(4, "news/189756ef-d036-4500-9205-57b3ee404c14_비자면제.jpg");
                pstmt.executeUpdate();

                pstmt.setString(1, "Notice");
                pstmt.setString(2, "K-ETAの申請方法がさらに簡素化されました");
                pstmt.setString(3, "韓国入国に必要な電子渡航許可（K-ETA）の申請プロセスがより分かりやすく、簡素化されました。事前の確認をお忘れなく！");
                pstmt.setString(4, "news/7e10cd0d-d4e0-43d4-9e5a-06e5656240a1_비자면제.jpg");
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("User data restoration attempt complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}