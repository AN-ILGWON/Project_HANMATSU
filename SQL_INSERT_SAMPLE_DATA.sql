-- ============================================
-- 韓まつ (ハンマツ) サンプルデータ挿入スクリプト
-- ============================================

-- 既存データの削除 (オプション: 競合を避けるため)
-- DELETE FROM hm_reply;
-- DELETE FROM hm_board_like;
-- DELETE FROM hm_board;
-- DELETE FROM hm_news;
-- DELETE FROM hm_festival;
-- DELETE FROM hm_banner;
-- DELETE FROM hm_member WHERE userid != 'admin';

-- 会員サンプルデータ
-- パスワード: "admin123" (BCryptハッシュ)
INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'matsuri_maniac', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '祭りハンター✨', 'hunter@hanmatsu.com', '010-1111-1111', '田中', '一郎', 'タナカ', 'イチロウ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'matsuri_maniac');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'beauty_otaku', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', 'コスメオタク女子', 'beauty@hanmatsu.com', '010-2222-2222', '佐藤', '美咲', 'サトウ', 'ミサキ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'beauty_otaku');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'spicy_club', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '激辛部。', 'spicy@hanmatsu.com', '010-3333-3333', '鈴木', '健太', 'スズキ', 'ケンタ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'spicy_club');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'weekend_korea', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '週末は韓国✈️', 'weekend@hanmatsu.com', '010-4444-4444', '高橋', '由美', 'タカハシ', 'ユミ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'weekend_korea');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'yuru_tabi', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', 'ゆるふわトラベラー', 'yuru@hanmatsu.com', '010-5555-5555', '伊藤', '直樹', 'イトウ', 'ナオキ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'yuru_tabi');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'kawaii_matsuri', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '祭りにゃん(｡･ω･｡)', 'kawaii@hanmatsu.com', '010-6666-6666', '渡辺', 'さくら', 'ワタナベ', 'サクラ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'kawaii_matsuri');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'tabetai_san', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', 'もぐもぐ韓国料理', 'tabetai@hanmatsu.com', '010-7777-7777', '小林', '裕二', 'コバヤシ', 'ユウジ', 'USER' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'tabetai_san');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
SELECT 'superadmin', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '韓まつ管理者', 'admin@hanmatsu.com', '010-0000-0000', '管理', '者', 'カンリ', 'シャ', 'ADMIN' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_member WHERE userid = 'superadmin');

-- 祭りサンプルデータ
INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, '江原道', '華川(ファチョン)ヤマメ祭り', '氷に穴を開けてヤマメを釣る、韓国を代表する冬の祭りです。素手でヤマメを捕まえる体験や、氷の彫刻展示など、冬ならではの楽しみが満載です. ', 
        TO_DATE('2026-01-10', 'YYYY-MM-DD'), TO_DATE('2026-02-02', 'YYYY-MM-DD'), 
        '江原道 華川郡', '36cb2c1f-c8c4-430e-bef7-fbd64fde27ae_화천산천어축제.jpg', 450, 'http://www.narafestival.com', 'https://www.instagram.com/hwacheon_sancheoneo/', 120);

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, 'ソウル', 'ソウル灯祭り', 'ソウルの中心部で開催される幻想的な灯の祭典です。清渓川に沿って数多くの美しい灯篭が並び、夜の街を彩ります。', 
        TO_DATE('2026-05-01', 'YYYY-MM-DD'), TO_DATE('2026-05-10', 'YYYY-MM-DD'), 
        'ソウル清渓川一帯', '7e99ca3c-9939-4c24-a0b1-808b741402b9_서울빛축제.jpg', 120, 'http://www.stolantern.com', 'https://www.instagram.com/seoullantern/', 50);

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, '慶尚南道', '鎮海(チネ)軍港祭', '韓国最大級の桜祭りです。36万本もの桜が一斉に咲き誇る姿は圧巻で、ロマンス橋や慶和駅などの有名な撮影スポットが人気です。', 
        TO_DATE('2026-03-25', 'YYYY-MM-DD'), TO_DATE('2026-04-03', 'YYYY-MM-DD'), 
        '慶尚南道 昌原市', '94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg', 350, 'http://culture.changwon.go.kr', 'https://www.instagram.com/changwon_city/', 100);

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, '江原道', '江陵(カンヌン)端午祭', 'ユネスコ無形文化遺産に登録された、韓国で最も歴史のある祭りの一つです。伝統的な儀式や仮面劇, 農楽などが楽しめます。', 
        TO_DATE('2026-06-18', 'YYYY-MM-DD'), TO_DATE('2026-06-25', 'YYYY-MM-DD'), 
        '江原道 江陵市', '6e0df438-8d9f-4e5f-bbaa-a93b55b69b8e_평창송어축제.jpg', 180, 'http://www.danojefestival.or.kr', 'https://www.instagram.com/gn_danoje/', 30);

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, '慶尚北道', '安東(アンドン)国際仮面舞フェスティバル', 'ユネスコ世界文化遺産に登録された河回村の伝統的な仮面劇を楽しめる祭りです。世界各国の仮面舞踊団も参加し、賑やかな公演が繰り広げられます。', 
        TO_DATE('2026-09-25', 'YYYY-MM-DD'), TO_DATE('2026-10-04', 'YYYY-MM-DD'), 
        '慶尚北道 安東市', '9f5a66ed-6269-4b6f-b9af-ccedbba7160a_공주군밤축제.jpg', 195, 'http://www.maskdance.com', 'https://www.instagram.com/andong_maskdance/', 40);

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile, views, homepage, instagram, likes) 
VALUES (hm_festival_seq.NEXTVAL, '済州', '済州野火まつり', '済州島の放牧文化から始まった、巨大な山を焼いて豊作を祈る情熱的な祭りです。夜空を彩る巨大な炎は、一生に一度は見たい絶景です。', 
        TO_DATE('2026-03-05', 'YYYY-MM-DD'), TO_DATE('2026-03-08', 'YYYY-MM-DD'), 
        '済州市 セビョルオルム', '7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg', 210, 'http://www.buriburi.go.kr', 'https://www.instagram.com/jeju_buriburi/', 60);

-- コミュニティボードサンプルデータ
INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'matsuri_maniac', '【速報】鎮海の桜が満開です！🌸', '今朝、鎮海（チネ）に到着しました！ロマンス橋の桜がちょうど満開で、本当に天国のようです。写真をシェアしますね！', 150, 45, SYSDATE, 'photo', '94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'spicy_club', '安東のチムダクはやっぱり本場が最高！🔥', '安東仮面舞フェスティバルのついでに、市場でチムダク를食べてきました。激辛で注文したら、口から火が出るかと思いましたが、クセになる美味しさです！', 85, 20, SYSDATE, 'review', '9f5a66ed-6269-4b6f-b9af-ccedbba7160a_공주군밤축제.jpg');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category) 
VALUES (hm_board_seq.NEXTVAL, 'beauty_otaku', 'オリーブヤングで爆買いしたもの紹介✨', '最近の韓国旅行でゲットしたおすすめコスメをまとめました。祭りの強い日差しで疲れた肌に効くパック, 最高です。', 210, 55, SYSDATE, 'free');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'matsuri_maniac', 'ソウルの隠れ家カフェ巡り☕', '聖水（ソンス）エリアに新しくできたカフェに行ってきました。お祭りの喧騒を忘れて、ゆったりした時間を過ごせます。', 120, 32, SYSDATE, 'photo', '8348cc5c-12fe-483d-8b22-0397ebf016df_성수카페5.jpg');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'yuru_tabi', 'マッドフェスティバル、汚れても大丈夫な服装は？🤔', '来月、初めて保寧のマッドフェスティバルに行きます。やっぱり白のTシャツは避けたほうがいいですよね？経験者の方、教えてください！', 65, 10, SYSDATE, 'free', 'a2cb29cd-5912-4b5d-b012-755412d9d5fc_대관령눈꽃축제.png');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'kawaii_matsuri', '済州島の野火まつり、マジで感動したにゃん(>ω<)', '昨日の夜、山が燃えるのを見て鳥肌立ちました！すごく綺麗で、お願い事もしてきました。みんなにも見てほしいですっ！', 110, 38, SYSDATE, 'review', '7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category, imgfile) 
VALUES (hm_board_seq.NEXTVAL, 'tabetai_san', '灯祭りの屋台料理選手権！🌭', '清渓川の灯祭りに行ってきましたが、結局花より団子（笑）。チーズハットグとトッポギが最高に美味しかったです。', 95, 25, SYSDATE, 'review', '7e99ca3c-9939-4c24-a0b1-808b741402b9_서울빛축제.jpg');

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate, category) 
VALUES (hm_board_seq.NEXTVAL, 'superadmin', '【お知らせ】2026年度版お祭りカレンダー公開', '日本の皆様向けに、2026年の主要なお祭りをカレンダーにまとめました。旅行の計画にお役立てください！', 500, 100, SYSDATE, 'notice');

-- ニュースサンプルデータ
INSERT INTO hm_news (nno, title, category, regdate, link_url) 
VALUES (hm_news_seq.NEXTVAL, '2026年韓国旅行の最新トレンドは「地域密着型」', 'Trend', SYSDATE, 'https://japanese.visitkorea.or.kr/');

INSERT INTO hm_news (nno, title, category, regdate, link_url) 
VALUES (hm_news_seq.NEXTVAL, 'K-ETAの申請方法がさらに簡素化されました', 'Notice', SYSDATE, 'https://www.k-eta.go.kr/');

INSERT INTO hm_news (nno, title, category, regdate, link_url) 
VALUES (hm_news_seq.NEXTVAL, 'ソウル直通列車の運行本数が拡大されます', 'Transport', SYSDATE, 'https://www.arex.or.kr/');

-- バナーサンプルデータ
INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) 
VALUES (hm_banner_seq.NEXTVAL, '2026 鎮海軍港祭へようこそ', '36万本の桜が織りなす絶景', '94f2aafa-668a-44e1-9cfc-77bd577f60c2_진해벚꽃.jpg', '/hanmatsu/festival/view.do?fno=2', 1, 'Y');

INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) 
VALUES (hm_banner_seq.NEXTVAL, '保寧マッドフェスティバル 早期予約受付中', '泥まみれになって楽しむ最高の夏！', 'ab004eb6-dae5-4b24-954e-1a5e9f2899c0_default_banner.jpg', '/hanmatsu/festival/view.do?fno=3', 2, 'Y');

INSERT INTO hm_banner (bano, title, subtitle, imgfile, link_url, order_no, is_active) 
VALUES (hm_banner_seq.NEXTVAL, '済州野火まつり 宿泊パッケージ', '燃え上がる山、忘れられない夜を', '7bdfad23-da92-499c-a0cd-d2cfd290f10b_제주들불축제.jpg', '/hanmatsu/festival/view.do?fno=5', 3, 'Y');

-- サイト情報 (Site Info)
INSERT INTO hm_site_info (info_key, title, content, updated_date)
SELECT 'about', '韓まつについて', '<h3>韓まつ（Hanmatsu）の由来</h3>
<p>「韓まつ」は、韓国の<b>「韓（ハン）」</b>と日本の<b>「まつり」</b>を掛け合わせた名前です。</p>
<p>私たちは、韓国の情熱的な祭りと日本の繊細な文化をつなぐ架け橋となり、両国の文化交流をより深めることを目的に誕生しました。</p>
<h3>私たちの使命</h3>
<ul>
    <li>正確で旬な韓国の祭り情報の提供</li>
    <li>日韓の旅行者が交流できるコミュニティの形成</li>
    <li>お祭りを通じた新しい韓国の魅力の発見</li>
</ul>
<p>韓まつと共に、忘れられない特別な韓国旅行に出かけましょう！</p>', SYSDATE FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_site_info WHERE info_key = 'about');

INSERT INTO hm_site_info (info_key, title, content, updated_date)
SELECT 'guide', '利用ガイド', '<p>韓まつを最大限に活用するためのガイドです。</p>
<h4>1. お祭りを探す</h4>
<p>「お祭り紹介」メニューから、地域別、日付別に祭りを検索できます。</p>
<h4>2. コミュニティに参加する</h4>
<p>会員登録をすると、お祭りの感想を投稿したり、他の旅行者と情報を共有したりできます。</p>', SYSDATE FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM hm_site_info WHERE info_key = 'guide');

COMMIT;
