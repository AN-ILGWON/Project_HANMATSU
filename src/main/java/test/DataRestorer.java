package test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBManager;

public class DataRestorer {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getInstance();
            if (conn == null) {
                System.err.println("Connection failed!");
                return;
            }
            conn.setAutoCommit(false);
            
            /* 0. 기존 데이터 삭제 (사용자 데이터 보호를 위해 주석 처리)
            String[] truncateSqls = {
                "DELETE FROM hm_reply",
                "DELETE FROM hm_board_like",
                "DELETE FROM hm_wishlist",
                "DELETE FROM hm_visited",
                "DELETE FROM hm_board",
                "DELETE FROM hm_festival",
                "DELETE FROM hm_news",
                "DELETE FROM hm_banner",
                "DELETE FROM hm_site_info",
                "DELETE FROM hm_category",
                "DELETE FROM hm_member"
            };
            for (String sql : truncateSqls) {
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                pstmt.close();
            }
            System.out.println("Old data cleared.");
            */

            // 1. 会員データ挿入
            System.out.println("Inserting members...");
            String memberSql = "INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(memberSql);
            
            Object[][] members = {
                {"matsuri_maniac", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "祭りハンター✨", "hunter@hanmatsu.com", "010-1111-1111", "田中", "一郎", "タナカ", "イチロウ", "USER"},
                {"beauty_otaku", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "コスメオタク女子", "beauty@hanmatsu.com", "010-2222-2222", "佐藤", "美咲", "サトウ", "ミサキ", "USER"},
                {"spicy_club", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "激辛部。", "spicy@hanmatsu.com", "010-3333-3333", "鈴木", "健太", "スズキ", "ケンタ", "USER"},
                {"weekend_korea", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "週末は韓国✈️", "weekend@hanmatsu.com", "010-4444-4444", "高橋", "由美", "タカハシ", "ユミ", "USER"},
                {"yuru_tabi", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "ゆるふわトラベラー", "yuru@hanmatsu.com", "010-5555-5555", "伊藤", "直樹", "イトウ", "ナオキ", "USER"},
                {"kawaii_matsuri", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "祭りにゃん(｡･ω･｡)", "kawaii@hanmatsu.com", "010-6666-6666", "渡辺", "さくら", "ワタナベ", "サクラ", "USER"},
                {"tabetai_san", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "もぐもぐ韓国料理", "tabetai@hanmatsu.com", "010-7777-7777", "小林", "裕二", "コバヤシ", "ユウジ", "USER"},
                {"superadmin", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "韓まつ管理者", "admin@hanmatsu.com", "010-0000-0000", "管理", "者", "カンリ", "シャ", "ADMIN"}
            };

            for (Object[] m : members) {
                for (int i = 0; i < m.length; i++) {
                    pstmt.setString(i + 1, (String)m[i]);
                }
                pstmt.executeUpdate();
            }
            System.out.println("Members inserted.");

            // 2. 축제 데이터 삽입 (21개 한국 지역 축제)
            String festSql = "INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes, is_recommended) VALUES (hm_festival_seq.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(festSql);
            
            Object[][] festivals = {
                {"忠清北道", "永同(ヨンドン)干し柿祭り", "甘くて美味しい干し柿をテーマにした冬の代表的な祭りです。", "2026-01-03", "2026-01-05", "永同郡 永同川一帯", "14c7d6fa-4cd8-496c-81fa-156942e0aeb7_영동곶감축제.jpg", 1200, "http://www.yd21.go.kr", "https://www.instagram.com/yeongdong_official/", 85, "Y"},
                {"江原道", "華川(ファチョン)ヤマメ祭り", "氷に穴を開けてヤマメを釣る、世界的な冬の祭りです。", "2026-01-10", "2026-02-02", "江原道 華川郡", "36cb2c1f-c8c4-430e-bef7-fbd64fde27ae_화천산천어축제.jpg", 2500, "http://www.narafestival.com", "https://www.instagram.com/hwacheon_sancheoneo/", 210, "Y"},
                {"慶尚南道", "鎮海(チネ)軍港祭", "36万本の桜が咲き誇る、韓国最大の桜祭りです。", "2026-03-25", "2026-04-03", "慶尚南道 昌原市", "94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg", 3500, "http://culture.changwon.go.kr", "https://www.instagram.com/changwon_city/", 450, "Y"},
                {"済州島", "済州(チェジュ)野火まつり", "山一面を燃やして豊作을 祈願하는, 幻想的な火の祭典です。", "2026-03-05", "2026-03-08", "済州市 セビョルオルム", "7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg", 1800, "http://www.buriburi.go.kr", "https://www.instagram.com/jeju_buriburi/", 150, "Y"},
                {"ソウル", "ソウル灯祭り", "清渓川を彩る美しい灯籠の数々。冬の夜をロマンチックに演出します。", "2026-11-01", "2026-11-17", "ソウル市 清渓川一帯", "7e99ca3c-9939-4c24-a0b1-808b741402b9_서울빛축제.jpg", 2200, "http://www.stolantern.com", "https://www.instagram.com/seoullantern/", 180, "Y"},
                {"忠清南道", "保寧(ボリョン)マッド祭り", "全世界から観光客が集まる、泥まみれになって楽しむ情熱的な祭りです。", "2026-07-19", "2026-07-28", "保寧市 大川海水浴場", "2ee36fb2-08a1-4c8e-a315-f54b1355d12d_머드축제보령.jpg", 5000, "http://www.mudfestival.or.kr", "https://www.instagram.com/boryeong_mud_festival/", 600, "Y"},
                {"京畿道", "抱川(ポチョン)冬の祭り", "美しい自然の中で楽しむ、家族向けの冬のアクティビティが満載です。", "2025-12-20", "2026-02-10", "抱川市 一帯", "0b927cc3-778c-4ba3-b71a-faec8a7bd834_포천축제.jpg", 800, "http://www.pocheon.go.kr", "", 45, "N"},
                {"大田", "大田(テジョン)0時祭り", "眠らない都市、大田の夏を熱く盛り上げる深夜の祭典です。", "2026-08-11", "2026-08-17", "大田広域市 中央路一帯", "402477aa-5695-407f-a67a-ca15e86d78ee_0시축제.jpg", 1500, "http://www.daejeon0festival.com", "", 95, "N"},
                {"江原道", "洪川(ホンチョン)コンコン祭り", "凍った川でワカサギ釣りを楽しむ、江原道の冬の風物詩です。", "2026-01-05", "2026-01-21", "洪川郡 洪川江", "4c1835ca-da8b-4704-bd0e-746dd0529ba2_홍천꽁꽁축제.jpg", 700, "http://www.hccf.or.kr", "", 38, "N"},
                {"京畿道", "楊平(ヤンピョン)ワカサギ祭り", "綺麗な水で育ったワカサギを釣って味わえる、人気の冬祭りです。", "2025-12-24", "2026-02-15", "楊平郡 一帯", "60d35cfd-3b94-4225-a36a-3c169978a813_양평빙송어.jpg", 600, "http://www.ypfestival.com", "", 32, "N"},
                {"釜山", "温泉川(オンチョンチョン)光祭り", "釜山の温泉川沿いに広がる、色鮮やかな光のアートが楽しめます。", "2026-04-10", "2026-04-15", "釜山市 温泉川", "6b08fa00-6686-4f7a-9670-c7c30feafc0f_온천천빛축제.png", 900, "http://www.busan.go.kr", "", 55, "N"},
                {"釜山", "釜山光復路(クァンボンノ)光祭り", "クリスマスシーズンに釜山の街を彩る、華やかなイルミネーションです。", "2025-12-01", "2026-01-31", "釜山市 中区 光復路", "6c2cf11c-3513-417e-84f4-e2c7839ca6fc_부산광복로축제1.jpg", 1800, "http://www.bsjunggu.go.kr", "", 120, "Y"},
                {"江原道", "平昌(ピョンチャン)鱒祭り", "オリンピックの街、平昌で開催される冬の鱒釣り体験祭りです。", "2025-12-28", "2026-02-02", "平昌郡 珍富面", "6e0df438-8d9f-4e5f-bbaa-a93b55b69b8e_평창송어축제.jpg", 1100, "http://www.festival700.or.kr", "", 75, "N"},
                {"各地", "菜の花祭り", "春の訪れを告げる、黄色い絨毯のような菜の花畑が絶景です。", "2026-04-01", "2026-04-30", "韓国各地の菜の花名所", "76bb66b5-12c5-4bdc-932e-5f50607305e7_유채꽃축제.png", 1400, "", "", 90, "N"},
                {"全羅南道", "咸平(ハムピョン)蝶祭り", "数万匹の蝶が舞う、自然と触れ合える癒やしのエコツーリズム祭りです。", "2026-04-26", "2026-05-06", "咸平郡 咸平エキスポ公園", "8a828f94-1ba9-4760-b6b3-b948414ef3c4_함평나비축제.png", 1300, "http://www.hampyeong.go.kr", "", 88, "N"},
                {"忠清南道", "公州(コンジュ)焼き栗祭り", "世界遺産の街、公州で美味しい焼き栗を堪能できる冬の味覚祭りです。", "2026-01-10", "2026-01-12", "公州市 錦江新官公園", "9f5a66ed-6269-4b6f-b9af-ccedbba7160a_공주군밤축제.jpg", 950, "http://www.gongju.go.kr", "", 62, "N"},
                {"江原道", "大関嶺(テグァルリョン)雪祭り", "巨大な雪像と雪遊びが楽しめる、韓国で最も歴史ある雪祭りです。", "2026-01-17", "2026-01-26", "平昌郡 大関嶺面", "a2cb29cd-5912-4b5d-b012-755412d9d5fc_대관령눈꽃축제.png", 1600, "http://www.snowfestival.net", "", 105, "N"},
                {"慶尚南道", "泗川(サチョン)エアショー", "迫力満点のアクロバット飛行が間近で見られる、航空宇宙の祭典です。", "2026-10-24", "2026-10-27", "泗川市 泗川飛行場", "bd8163aa-7581-43bb-97b7-02069f3d308c_사천에어쇼0.png", 2000, "http://airshow.sacheon.go.kr", "", 140, "N"},
                {"京畿道", "安城(アンソン)ワカサギ祭り", "冬の氷上釣りと伝統遊びが一緒に楽しめる、家族에 人気の祭りです。", "2025-12-21", "2026-02-09", "安城市 一帯", "de7e9bca-99fa-46a1-ac63-4d07b9fd8c59_안성빙어축제.jpg", 550, "http://www.anseongfestival.com", "", 28, "N"},
                {"釜山", "釜山国際映画祭", "アジア最大級の映画祭。世界中の映画人とファンが集まります。", "2026-10-02", "2026-10-11", "釜山市 海雲台・映画の殿堂", "1fe1d070-0a67-47ef-9b04-095f10b636aa_busankokusai.jpg", 4500, "http://www.biff.kr", "https://www.instagram.com/busanfilmfest/", 550, "Y"},
                {"慶尚北道", "安東(アンドン)国際仮面舞フェスティバル", "ユネスコ無形文化遺産に登録された、韓国を代表する伝統祭りの一つです。", "2025-09-26", "2025-10-05", "安東市 旧安東駅一帯", "9f5a66ed-6269-4b6f-b9af-ccedbba7160a_공주군밤축제.jpg", 3200, "http://www.maskdance.com", "https://www.instagram.com/andong_maskdance/", 245, "N"},
                {"仁川", "席毛島(ソンモド)温泉祭り", "西海の夕日を眺めながら温泉を楽しめる、心身ともに温まる祭りです。", "2025-11-15", "2025-11-30", "江華郡 席毛島", "5bd80a02-f1aa-45b6-bd62-1c220d4f953e_석모도온천_배너.jpg", 1000, "http://www.ganghwa.go.kr", "", 70, "N"}
            };

            for (Object[] f : festivals) {
                pstmt.setString(1, (String)f[0]);
                pstmt.setString(2, (String)f[1]);
                pstmt.setString(3, (String)f[2]);
                pstmt.setString(4, (String)f[3]);
                pstmt.setString(5, (String)f[4]);
                pstmt.setString(6, (String)f[5]);
                pstmt.setString(7, (String)f[6]);
                pstmt.setInt(8, (Integer)f[7]);
                pstmt.setString(9, (String)f[8]);
                pstmt.setString(10, (String)f[9]);
                pstmt.setInt(11, (Integer)f[10]);
                pstmt.setString(12, (String)f[11]);
                pstmt.executeUpdate();
            }
            System.out.println("21 Korean festivals inserted.");

            // 2.5 카테고리 데이터 삽입
            System.out.println("Inserting category data...");
            String catSql = "INSERT INTO hm_category (cno, name, type) VALUES (hm_category_seq.NEXTVAL, ?, ?)";
            pstmt = conn.prepareStatement(catSql);
            
            Object[][] categories = {
                {"祭りレビュー", "BOARD"},
                {"自由掲示板", "BOARD"},
                {"Q&A", "BOARD"},
                {"お知らせ", "BOARD"},
                {"Trend", "NEWS"},
                {"Notice", "NEWS"},
                {"Transport", "NEWS"}
            };
            
            for (Object[] c : categories) {
                pstmt.setString(1, (String)c[0]);
                pstmt.setString(2, (String)c[1]);
                pstmt.executeUpdate();
            }
            System.out.println("Category data inserted.");

            // 3. 게시판 데이터 삽입 (일본어)
            System.out.println("Inserting board data...");
            String boardSql = "INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) VALUES (hm_board_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
            pstmt = conn.prepareStatement(boardSql);
            
            Object[][] boards = {
                {"matsuri_maniac", "【速報】鎮海の桜が満開です！🌸", "今朝、鎮海（チネ）に到着しました！ロマンス橋の桜가 ちょうど満開で、本当に天国のようです。写真をシェアしますね！", 150, 45, "祭りレビュー", "94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg"},
                {"spicy_club", "安東のチムダクはやっぱり本場が最高！🔥", "安東仮면舞フェスティバル의 ついでに、市場でチムダクを食べました。激辛で注文したら、口から火が出るかと思いましたが、クセになる美味しさです！", 85, 20, "祭りレビュー", "9f5a66ed-6269-4b6f-b9af-ccedbba7160a_공주군밤축제.jpg"},
                {"beauty_otaku", "オリーブヤングで爆買いしたもの紹介✨", "最近の韓国旅行でゲットしたおすすめコスメをまとめました。祭りの強い日差しで疲れた肌に効くパック、最高です。", 210, 55, "自由掲示板", null},
                {"matsuri_maniac", "ソウルの隠れ家カフェ巡り☕", "聖水（ソンス）エリアに新しくできたカフェに行ってきました。お祭りの喧騒を忘れて、ゆったりした時間を過ごせます。", 120, 32, "祭りレビュー", "8348cc5c-12fe-483d-8b22-0397ebf016df_성수카페5.jpg"},
                {"yuru_tabi", "マッドフェスティバル、汚れて도 大丈夫な服装は？🤔", "来月、初めて保寧のマッドフェスティバルに行きます。やっぱり白의 Tシャツは避けたほうがいいですよね？経験者の方、教えてください！", 65, 10, "自由掲示板", "a2cb29cd-5912-4b5d-b012-755412d9d5fc_대관령눈꽃축제.png"},
                {"kawaii_matsuri", "済州島の野火まつり、マジで感動したにゃん(>ω<)", "昨日の夜、山が燃えるのを見て鳥肌立ちました！すごく綺麗で、お願い事もしてきました。みんなにも見てほしいですっ！", 110, 38, "祭りレビュー", "7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg"},
                {"tabetai_san", "灯祭りの屋台料理選手権！🌭", "清渓川の灯祭りに行ってきましたが、結局花より団子（笑）。チーズハットグとトッポギが最高に美味しかったです。", 95, 25, "祭りレビュー", "7e99ca3c-9939-4c24-a0b1-808b741402b9_서울빛축제.jpg"},
                {"superadmin", "【お知らせ】2026年度版お祭りカレンダー公開", "日本の皆様向けに、2026年の主要なお祭리를 カレンダーにまとめました。旅行의 計画にお役立てください！", 500, 100, "お知らせ", null}
            };

            for (Object[] b : boards) {
                pstmt.setString(1, (String)b[0]);
                pstmt.setString(2, (String)b[1]);
                pstmt.setString(3, (String)b[2]);
                pstmt.setInt(4, (Integer)b[3]);
                pstmt.setInt(5, (Integer)b[4]);
                pstmt.setString(6, (String)b[5]);
                pstmt.setString(7, (String)b[6]);
                pstmt.executeUpdate();
            }
            System.out.println("Board data inserted.");

            // 4. 뉴스 데이터 삽입
            String newsSql = "INSERT INTO hm_news (nno, title, category, content, regdate, link_url) VALUES (hm_news_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
            pstmt = conn.prepareStatement(newsSql);
            
            Object[][] newsData = {
                {"2026年韓国旅行の最新トレンドは「地域密着型」", "Trend", "2026年の韓国旅行は、有名な観光地だけでなく、その土地ならではの文化や祭りを体験する「地域密着型」が注目されています。", "https://japanese.visitkorea.or.kr/"},
                {"K-ETAの申請方法がさらに簡素化されました", "Notice", "韓国入国に必要な電子渡航許可（K-ETA）の申請プロセスがより分かりやすく、簡素化されました。事前の確認をお忘れなく！", "https://www.k-eta.go.kr/"},
                {"ソウル直通列車の運行本数が拡大されます", "Transport", "仁川空港からソウル市内へのアクセスがより便利になります。直通列車の運行本数が増え、待ち時間が短縮されます。", "https://www.arex.or.kr/"}
            };

            for (Object[] n : newsData) {
                pstmt.setString(1, (String)n[0]);
                pstmt.setString(2, (String)n[1]);
                pstmt.setString(3, (String)n[2]);
                pstmt.setString(4, (String)n[3]);
                pstmt.executeUpdate();
            }
            System.out.println("News data inserted.");

            // 5. 배너 데이터 삽입
            String bannerSql = "INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) VALUES (hm_banner_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(bannerSql);
            
            Object[][] banners = {
                {"2026 鎮海軍港祭へようこそ", "36万本の桜が織りなす絶景", "94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg", "/hanmatsu/festival/list.do", 1, "Y"},
                {"保寧マッドフェスティバル 早期予約受付中", "泥まみれになって楽しむ最高の夏！", "2ee36fb2-08a1-4c8e-a315-f54b1355d12d_머드축제보령.jpg", "/hanmatsu/festival/list.do", 2, "Y"},
                {"済州野火まつり 宿泊パッケージ", "燃え上がる山、忘れられない夜を", "7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg", "/hanmatsu/festival/list.do", 3, "Y"}
            };

            for (Object[] ba : banners) {
                pstmt.setString(1, (String)ba[0]);
                pstmt.setString(2, (String)ba[1]);
                pstmt.setString(3, (String)ba[2]);
                pstmt.setString(4, (String)ba[3]);
                pstmt.setInt(5, (Integer)ba[4]);
                pstmt.setString(6, (String)ba[5]);
                pstmt.executeUpdate();
            }
            System.out.println("Banner data inserted.");

            // 6. 사이트 정보 삽입
            String infoSql = "INSERT INTO hm_site_info (info_key, title, content, updated_date) VALUES (?, ?, ?, SYSDATE)";
            pstmt = conn.prepareStatement(infoSql);
            pstmt.setString(1, "about");
            pstmt.setString(2, "韓まつについて");
            pstmt.setString(3, "<h3>韓まつ（Hanmatsu）の由来</h3><p>「韓まつ」は、韓国の<b>「韓（ハン）」</b>と日本の<b>「まつり」</b>を掛け合わせた名前です。</p><p>韓国の魅力的な地域祭りを日本の皆様に紹介し、文化交流の架け橋となることを目指しています。</p>");
            pstmt.executeUpdate();

            conn.commit();
            System.out.println("Data restoration complete!");

            // fno 확인을 위해 출력 (나중에 링크 수정을 위해)
            System.out.println("--- Festival List (for link fix) ---");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT fno, name FROM hm_festival ORDER BY fno")) {
                while (rs.next()) {
                    System.out.println("fno: " + rs.getInt("fno") + " | name: " + rs.getString("name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (Exception re) {}
            }
        } finally {
            util.DBManager.close(conn, pstmt);
        }
    }
}
