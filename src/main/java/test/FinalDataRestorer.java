package test;
import java.sql.*;
import util.DBManager;

public class FinalDataRestorer {
    public static void main(String[] args) {
        try (Connection conn = DBManager.getInstance()) {
            conn.setAutoCommit(false);
            
            // 기존 테이블 데이터 초기화
            String[] tables = {
                "hm_reply", "hm_board_like", "hm_wishlist", "hm_visited",
                "hm_board", "hm_festival", "hm_news", "hm_banner",
                "hm_site_info", "hm_category", "hm_member"
            };
            try (Statement stmt = conn.createStatement()) {
                for (String table : tables) {
                    stmt.executeUpdate("DELETE FROM " + table);
                }
            }
            System.out.println("기존 테이블 데이터 삭제 완료.");

            // 테스트용 회원 데이터 추가
            String memberSql = "INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(memberSql)) {
                Object[][] members = {
                    {"kawaii_matsuri", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "祭りの妖精🌸", "kawaii@hanmatsu.com", "010-7777-7777", "春野", "さくら", "ハルノ", "サクラ", "USER"},
                    {"matsuri_maniac", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "祭りハンター✨", "hunter@hanmatsu.com", "010-1111-1111", "田中", "一郎", "タナカ", "イチロウ", "USER"},
                    {"beauty_otaku", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "コスメオタク女子", "beauty@hanmatsu.com", "010-2222-2222", "佐藤", "美咲", "サトウ", "미사키", "USER"},
                    {"spicy_club", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "激辛部。", "spicy@hanmatsu.com", "010-3333-3333", "鈴木", "健太", "スズキ", "ケンタ", "USER"},
                    {"superadmin", "$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6", "韓まつ管理者", "admin@hanmatsu.com", "010-0000-0000", "管理", "者", "カンリ", "シャ", "ADMIN"}
                };
                for (Object[] m : members) {
                    for (int i = 0; i < m.length; i++) pstmt.setString(i + 1, (String)m[i]);
                    pstmt.executeUpdate();
                }
            }

            // 카테고리 설정
            String catSql = "INSERT INTO hm_category (cno, name, type) VALUES (hm_category_seq.NEXTVAL, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(catSql)) {
                String[][] categories = {
                    {"祭りレビュー", "BOARD"}, {"自由掲示판", "BOARD"}, {"Q&A", "BOARD"},
                    {"お知らせ", "BOARD"}, {"Trend", "NEWS"}, {"Notice", "NEWS"}
                };
                for (String[] c : categories) {
                    pstmt.setString(1, c[0]); pstmt.setString(2, c[1]);
                    pstmt.executeUpdate();
                }
            }

            // 메인 배너 데이터 (이미지 경로 수정 반영)
            String bannerSql = "INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) VALUES (hm_banner_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(bannerSql)) {
                // Banner 1 - Cherry Blossom (Fixed UUID)
                pstmt.setString(1, "🌸 桜色に染まる、夢の韓旅へ 🌸");
                pstmt.setString(2, "36万本の桜가 織りなす、ロマンチックな春の散歩道。");
                pstmt.setString(3, "banner/7cc67013-9470-43aa-9a26-7fb42fb2f15b_진해벚꽃.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 1);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();

                // Banner 2 - Slide 1
                pstmt.setString(1, "✨ きらめくお祭りの魔法にかかって ✨");
                pstmt.setString(2, "泥まみれになって笑い合えば、最高の夏の思い出に！");
                pstmt.setString(3, "banner/4a55da82-a115-4176-8ae5-24eab05a3695_슬라이드 배너1.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 2);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();

                // Banner 3 - Slide 2
                pstmt.setString(1, "🔥 夜空を焦がす、神秘的な火の祭典 🔥");
                pstmt.setString(2, "燃え上がる山に願いを込めて、忘れられない感動を.");
                pstmt.setString(3, "banner/d33c3d74-b068-4f83-b004-ed103f659cdb_슬라이드 배너2.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 3);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();

                // Banner 4 - Hot Springs
                pstmt.setString(1, "♨️ 心も体もぽかぽか、癒やしの名湯巡り ♨️");
                pstmt.setString(2, "伝統ある温泉で、日常の疲れを優しく解きほぐして。");
                pstmt.setString(3, "banner/daa7c14f-b9db-4977-97e0-76ffdc2bdb08_석모도온천_배너.jpg");
                pstmt.setString(4, "/hanmatsu/festival/list.do");
                pstmt.setInt(5, 4);
                pstmt.setString(6, "Y");
                pstmt.executeUpdate();
            }

            // 게시판 샘플 포스팅
            String boardSql = "INSERT INTO hm_board (bno, userid, category, title, content, imgfile, regdate, views, likes) VALUES (hm_board_seq.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(boardSql)) {
                // Post 1 - Cherry Blossom
                pstmt.setString(1, "kawaii_matsuri");
                pstmt.setString(2, "祭りレビュー");
                pstmt.setString(3, "🌸 桜の精霊に会いに来ちゃった！鎮海の桜、満開だにゃん 🌸");
                pstmt.setString(4, "今朝、鎮海（チネ）に到着しました！ロマンス橋の桜がちょうど満開で、本当に天国のようです。");
                pstmt.setString(5, "a3aecba2-fe08-482b-b15b-4c172e3602ba_진해벚꽃.jpg");
                pstmt.setInt(6, 150);
                pstmt.setInt(7, 45);
                pstmt.executeUpdate();

                // Post 2 - Olive Young
                pstmt.setString(1, "beauty_otaku");
                pstmt.setString(2, "自由掲示板");
                pstmt.setString(3, "✨ オリーブヤングで「可愛さ」を爆買いしちゃったにゃん ✨");
                pstmt.setString(4, "最近の韓国旅行でゲットしたおすすめコスメをまとめました。祭りの強い日差しで疲れた肌に効くパック、最高です。");
                pstmt.setString(5, "148f7d6b-b780-4d11-bfd2-3803915159d3_올리브영.jpg");
                pstmt.setInt(6, 210);
                pstmt.setInt(7, 55);
                pstmt.executeUpdate();

                // Post 3 - Seongsu Cafe
                pstmt.setString(1, "kawaii_matsuri");
                pstmt.setString(2, "祭りレビュー");
                pstmt.setString(3, "☕ 聖水のカフェで, ほっこり癒やしのティータイム ☕");
                pstmt.setString(4, "聖水（ソンス）エリアに新しくできたカフェに行ってきました。お祭りの喧騒を忘れて、ゆったりした時間を過ごせます。");
                pstmt.setString(5, "8348cc5c-12fe-483d-8b22-0397ebf016df_성수카페5.jpg");
                pstmt.setInt(6, 120);
                pstmt.setInt(7, 32);
                pstmt.executeUpdate();

                // Post 4 - Hot Spring (Fixed Path)
                pstmt.setString(1, "kawaii_matsuri");
                pstmt.setString(2, "祭りレビュー");
                pstmt.setString(3, "♨️ 冬の温泉旅行、心も体もぽかぽかに ✨");
                pstmt.setString(4, "寒い冬にはやっぱり温泉ですね！石毛島（ソンモド）の露天風呂、最高でした。海を見ながらの入浴は一生の思い出です。");
                pstmt.setString(5, "5bd80a02-f1aa-45b6-bd62-1c220d4f953e_석모도온천_배너.jpg");
                pstmt.setInt(6, 85);
                pstmt.setInt(7, 24);
                pstmt.executeUpdate();
            }

            // 축제 상세 정보 등록
            String festSql = "INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, regdate, homepage, instagram, map_url, likes, is_recommended) VALUES (hm_festival_seq.NEXTVAL, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(festSql)) {
                // Festival 1 - Jinhae Cherry Blossom (Recommended)
                pstmt.setString(1, "慶尚南道");
                pstmt.setString(2, "鎮海軍港祭 (진해군항제)");
                pstmt.setString(3, "36万本の桜が咲き誇る、韓国最大の桜祭りです。");
                pstmt.setString(4, "2026-03-25");
                pstmt.setString(5, "2026-04-03");
                pstmt.setString(6, "慶尚南道 昌原市 鎮海区");
                pstmt.setString(7, "a3aecba2-fe08-482b-b15b-4c172e3602ba_진해벚꽃.jpg");
                pstmt.setInt(8, 500);
                pstmt.setString(9, "http://jg_matsuri.com");
                pstmt.setString(10, "@jinhae_cherry");
                pstmt.setString(11, "https://maps.google.com/?q=Jinhae+Gunhangje");
                pstmt.setInt(12, 120);
                pstmt.setString(13, "Y");
                pstmt.executeUpdate();

                // Festival 2 - Boryeong Mud Festival
                pstmt.setString(1, "忠清南道");
                pstmt.setString(2, "保寧マッドフェスティバル (보령머드축제)");
                pstmt.setString(3, "世界中の人が集まる、エネルギッシュな泥の祭典！");
                pstmt.setString(4, "2026-07-18");
                pstmt.setString(5, "2026-07-27");
                pstmt.setString(6, "忠清南道 保寧市 大川海水浴場");
                pstmt.setString(7, "2ee36fb2-08a1-4c8e-a315-f54b1355d12d_머드축제보령.jpg");
                pstmt.setInt(8, 800);
                pstmt.setString(9, "http://mudfestival.kr");
                pstmt.setString(10, "@boryeongmud");
                pstmt.setString(11, "https://maps.google.com/?q=Boryeong+Mud+Festival");
                pstmt.setInt(12, 250);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();

                // Festival 5 - Andong Mask Dance Festival
                pstmt.setString(1, "慶尚北道");
                pstmt.setString(2, "安東国際仮面舞フェスティバル (안동국제탈춤페스티벌)");
                pstmt.setString(3, "伝統的な仮面舞踊と現代のパフォーマンスが融合したお祭り！");
                pstmt.setString(4, "2026-09-25");
                pstmt.setString(5, "2026-10-04");
                pstmt.setString(6, "慶尚北道 安東市");
                pstmt.setString(7, "banner/7cc67013-9470-43aa-9a26-7fb42fb2f15b_진해벚꽃.jpg"); // 임시 이미지
                pstmt.setInt(8, 600);
                pstmt.setString(9, "http://maskdance.com");
                pstmt.setString(10, "@andong_mask");
                pstmt.setString(11, "https://maps.google.com/?q=Andong+Maskdance+Festival");
                pstmt.setInt(12, 150);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();

                // Festival 6 - Busan Fireworks Festival
                pstmt.setString(1, "釜山");
                pstmt.setString(2, "釜山花火大会 (부산불꽃축제)");
                pstmt.setString(3, "広安大橋を背景に繰り広げられる、幻想的な光の饗宴。");
                pstmt.setString(4, "2026-11-07");
                pstmt.setString(5, "2026-11-07");
                pstmt.setString(6, "釜山 広安里海水浴場");
                pstmt.setString(7, "banner/4a55da82-a115-4176-8ae5-24eab05a3695_슬라이드 배너1.jpg"); // 임시 이미지
                pstmt.setInt(8, 1200);
                pstmt.setString(9, "http://bfo.or.kr");
                pstmt.setString(10, "@busan_fireworks");
                pstmt.setString(11, "https://maps.google.com/?q=Gwangalli+Beach");
                pstmt.setInt(12, 300);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();

                // Festival 7 - Hwacheon Sancheoneo Ice Festival
                pstmt.setString(1, "江原道");
                pstmt.setString(2, "華川山魚氷まつり (화천산천어축제)");
                pstmt.setString(3, "氷の上で釣りを楽しむ、韓国を代表する冬の祭典！");
                pstmt.setString(4, "2026-01-10");
                pstmt.setString(5, "2026-02-01");
                pstmt.setString(6, "江原道 華川郡");
                pstmt.setString(7, "a2cb29cd-5912-4b5d-b012-755412d9d5fc_대관령눈꽃축제.png"); // 임시 이미지
                pstmt.setInt(8, 900);
                pstmt.setString(9, "http://narafestival.com");
                pstmt.setString(10, "@hwacheon_sancheoneo");
                pstmt.setString(11, "https://maps.google.com/?q=Hwacheon+Sancheoneo+Festival");
                pstmt.setInt(12, 210);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();

                // Festival 3 - Jeju Fire Festival
                pstmt.setString(1, "済州道");
                pstmt.setString(2, "済州野火まつり (제주들불축제)");
                pstmt.setString(3, "無病息災を祈り、山을 燃やす幻想的なお祭り。");
                pstmt.setString(4, "2026-03-07");
                pstmt.setString(5, "2026-03-09");
                pstmt.setString(6, "済州市 涯月邑 ソンビョルオルム");
                pstmt.setString(7, "7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg");
                pstmt.setInt(8, 300);
                pstmt.setString(9, "http://buriburi.go.kr");
                pstmt.setString(10, "@jeju_fire");
                pstmt.setString(11, "https://maps.google.com/?q=Jeju+Fire+Festival");
                pstmt.setInt(12, 95);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();

                // Festival 4 - Winter Strawberry Festival (This Month: January 2026)
                pstmt.setString(1, "忠清南道");
                pstmt.setString(2, "論山イチゴ祭り (논산딸기축제)");
                pstmt.setString(3, "甘〜いイチゴの香りに包まれて、幸せな冬のひとときを。");
                pstmt.setString(4, "2026-01-15");
                pstmt.setString(5, "2026-01-31");
                pstmt.setString(6, "忠清南道 論山市 論山川ドゥンチ");
                pstmt.setString(7, "a2cb29cd-5912-4b5d-b012-755412d9d5fc_대관령눈꽃축제.png");
                pstmt.setInt(8, 450);
                pstmt.setString(9, "http://nonsan.go.kr/strawberry");
                pstmt.setString(10, "@nonsan_strawberry");
                pstmt.setString(11, "https://maps.google.com/?q=Nonsan+Strawberry+Festival");
                pstmt.setInt(12, 180);
                pstmt.setString(13, "N");
                pstmt.executeUpdate();
            }

            // 최신 뉴스 데이터 등록
            String newsSql = "INSERT INTO hm_news (nno, title, category, content, imgfile, link_url, regdate) VALUES (hm_news_seq.NEXTVAL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement pstmt = conn.prepareStatement(newsSql)) {
                String[][] newsItems = {
                    {
                        "2026年、韓国各地で大型地域祭り開催へ　外国人観光客誘致に期待", "祭典", 
                        "韓国各地の自治体は2026年、地域の特色を生かした大型祭りを相次いで開催する予定だ。文化体育観光部と地方自治体は、外国人観光客の誘致と地域経済の活性化を目的に、祭りを観光資源として積極的に活用している。\n\nソウルでは伝統文化と現代コンテンツを融合させた都市型フェスティバルが予定されており、伝統公演、夜間イベント、体験型プログラムなどが企画されている。地方都市でも、地域の歴史や自然, 食文化をテーマにした祭りが開催される見通しだ。\n\n特に日本人観光客向けには、日本語案内の充実や高速鉄道を利用したアクセスの良さを強調した広報が行われる予定で、2026年は「祭りを目的とした韓国旅行」がさらに注目される年になりそうだ。", 
                        "news/69e374dd-94ac-4129-be7b-6aa0f7159d2e_축제1.jpg", null
                    },
                    {
                        "高速鉄道の利便性向上、韓国旅行がより身近に", "旅行", 
                        "韓国では高速鉄道網の発達により、国内移動の利便性が大きく向上している。ソウルと地方主要都市を結ぶKTX（韓国高速鉄道）は、観光客の移動手段として欠かせない存在となっている。\n\n仁川国際空港からソウル市内へは空港鉄道が運行されており、到着後すぐに高速鉄道へ乗り継ぐことが可能だ。これにより、日本人観光客は短時間で釜山や大邱、光州などの地方都市へ移動できるようになった。\n\n特に釜山まではソウルから約2時間半で到着でき、日程の限られた旅行者にとって大きなメリットとなっている。交通インフラの整備は、韓国旅行の満足度向上につながっており、今後さらなる利用拡大が期待されている。", 
                        "news/5871c9a7-d199-492b-9ba0-931007c3a128_ktx.jpg", null
                    },
                    {
                        "日本人観光客が再注目、近距離海外としての韓国旅行", "旅行", 
                        "日本人観光客の間で、韓国旅行への関心が再び高まっている。短い移動時間と手頃な旅行費用に加え、食文化やエンターテインメントの多様性が評価されている。\n\nソウルでは、景福宮や北村韓屋村などの伝統的な観光地に加え、カフェ巡りやショッピングを楽しむ若者層の姿が多く見られる。特にK-POPや韓国ドラマの影響により、関連スポットを訪れる「聖地巡礼型観光」が人気を集めている。\n\nまた、日本語対応が可能な店舗や案内表示が増えており、海外旅行に不慣れな人でも安心して訪問できる環境が整っている点も、旅行先として選ばれる理由の一つだ。今後も近距離海外旅行の選択肢として、韓国の存在感はさらに高まると予想されている。", 
                        "news/23466f67-8c4c-4d0e-a193-7a48899c27b3_비짓코리아.png", null
                    },
                    {
                        "2026年韓国旅行の最新トレンドは「地域密着型」", "旅行", 
                        "2026年の韓国旅行は、有名な観光地だけでなく、その土地ならではの文化や祭りを体験する「地域密着型」が注目されています。", 
                        "news/3f6fd4f8-d77a-4a2f-bc34-cfb971fddb00_온천_배너.pg.jpg", null
                    },
                    {
                        "K-ETAの申請方法がさらに簡素化されました", "Notice", 
                        "韓国入国に必要な電子渡航許可（K-ETA）の申請プロセスがより分かりやすく、簡素化されました。事前の確認をお忘れなく！", 
                        "news/3e349da0-8883-42f3-8ee9-ae36770738ed_비자면제.jpg", null
                    }
                };
                for (String[] n : newsItems) {
                    pstmt.setString(1, n[0]);
                    pstmt.setString(2, n[1]);
                    pstmt.setString(3, n[2]);
                    pstmt.setString(4, n[3]);
                    pstmt.setString(5, n[4]);
                    pstmt.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("모든 샘플 데이터 복구 성공!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
