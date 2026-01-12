-- ============================================
-- 韓まつ (ハンマツ) サンプルデータ挿入スクリプト
-- ============================================
-- 注意: このスクリプトは既にテーブルが作成されていることを前提としています
-- SQL_CREATE_TABLES.sql を先に実行してください

-- 会員サンプルデータ
-- パスワード: "admin123" (BCryptハッシュ)
INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
VALUES ('superadmin', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', '最高管理者', 'superadmin@hanmatsu.com', '010-1234-5678', '最高', '管理', 'サイコウ', 'カンリ', 'ADMIN');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
VALUES ('user1', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', 'ユーザー1', 'user1@hanmatsu.com', '010-1111-1111', '山田', '太郎', 'ヤマダ', 'タロウ', 'USER');

INSERT INTO hm_member (userid, password, nickname, email, phone, last_name_kanji, first_name_kanji, last_name_kana, first_name_kana, role) 
VALUES ('user2', '$2a$10$gu4MpXDVhUOQMbhwOHYz3O6pa.CdAEDWLKZA325o04BKpPaFrJQN6', 'ユーザー2', 'user2@hanmatsu.com', '010-2222-2222', '佐藤', '花子', 'サトウ', 'ハナコ', 'USER');

-- 祭りサンプルデータ
INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile) 
VALUES (hm_festival_seq.NEXTVAL, 'ソウル', 'ソウル灯祭り', 'ソウルで開催される美しい灯祭りです。夜になると数万個의 灯が点灯され、幻想的な雰囲気を楽しめます。', 
        TO_DATE('2026-05-01', 'YYYY-MM-DD'), TO_DATE('2026-05-10', 'YYYY-MM-DD'), 
        'ソウル市庁前広場', 'festival1.jpg');

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile) 
VALUES (hm_festival_seq.NEXTVAL, '釜山', '釜山国際映画祭', 'アジア最大級の映画祭です。世界各国の映画が上映され、多くの映画ファンが集まります。', 
        TO_DATE('2026-10-01', 'YYYY-MM-DD'), TO_DATE('2026-10-10', 'YYYY-MM-DD'), 
        '釜山映画の殿堂', 'festival2.jpg');

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile) 
VALUES (hm_festival_seq.NEXTVAL, '済州', '済州桜祭り', '済州島の美しい桜を楽しむ祭りです。春の訪れを感じながら、桜の下でピクニックを楽しめます。', 
        TO_DATE('2026-04-01', 'YYYY-MM-DD'), TO_DATE('2026-04-15', 'YYYY-MM-DD'), 
        '済州島桜通り', 'festival3.jpg');

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile) 
VALUES (hm_festival_seq.NEXTVAL, '全州', '全州韓紙文化祭', '韓国の伝統的な韓紙（ハンジ）文化を体験できる祭りです。伝統工芸体験や展示会が開催されます。', 
        TO_DATE('2026-09-15', 'YYYY-MM-DD'), TO_DATE('2026-09-20', 'YYYY-MM-DD'), 
        '全州韓屋村', 'festival4.jpg');

INSERT INTO hm_festival (fno, region, name, description, start_date, end_date, location, imgfile) 
VALUES (hm_festival_seq.NEXTVAL, '大邱', '大邱国際音楽祭', '国内外の著名なアーティストが参加する音楽祭です。様々なジャンルの音楽を楽しめます。', 
        TO_DATE('2026-07-20', 'YYYY-MM-DD'), TO_DATE('2026-07-27', 'YYYY-MM-DD'), 
        '大邱スタジアム', 'festival5.jpg');

-- コミュニティボードサンプルデータ
INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate) 
VALUES (hm_board_seq.NEXTVAL, 'user1', 'ソウル灯祭りに行ってきました！', '先週末、ソウル灯祭りに行ってきました。本当に美しくて感動しました。特に夜のライトアップが素晴らしかったです。', 0, 5, SYSDATE);

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate) 
VALUES (hm_board_seq.NEXTVAL, 'user2', '釜山国際映画祭のチケット購入方法', '釜山国際映画祭のチケットを購入する方法についてまとめました。事前予約がおすすめです。', 0, 8, SYSDATE);

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate) 
VALUES (hm_board_seq.NEXTVAL, 'user1', '済州島の桜祭り、おすすめスポット', '済州島の桜祭りでおすすめのスポットを紹介します。特に桜通りが美しいです。', 0, 12, SYSDATE);

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate) 
VALUES (hm_board_seq.NEXTVAL, 'superadmin', '韓国の祭りについて', '韓国には様々な地域祭りがあります。それぞれの特徴を楽しんでください。', 0, 15, SYSDATE);

INSERT INTO hm_board (bno, userid, title, content, views, likes, regdate) 
VALUES (hm_board_seq.NEXTVAL, 'user2', '全州韓紙文化祭の体験レポート', '全州韓紙文化祭で伝統工芸を体験しました。とても面白かったです。', 0, 7, SYSDATE);

-- いいねサンプルデータ
INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 1, 'user2', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 1, 'superadmin', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 2, 'user1', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 2, 'superadmin', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 3, 'user2', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 3, 'superadmin', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 4, 'user1', SYSDATE);

INSERT INTO hm_board_like (like_no, bno, userid, regdate) 
VALUES (hm_like_seq.NEXTVAL, 4, 'user2', SYSDATE);

-- コメントサンプルデータ
INSERT INTO hm_reply (rno, bno, userid, content, regdate) 
VALUES (hm_reply_seq.NEXTVAL, 1, 'user2', '私も行きました！本当に美しかったです。', SYSDATE);

INSERT INTO hm_reply (rno, bno, userid, content, regdate) 
VALUES (hm_reply_seq.NEXTVAL, 1, 'superadmin', '来年も開催される予定です。', SYSDATE);

INSERT INTO hm_reply (rno, bno, userid, content, regdate) 
VALUES (hm_reply_seq.NEXTVAL, 2, 'user1', 'チケット情報ありがとうございます！', SYSDATE);

INSERT INTO hm_reply (rno, bno, userid, content, regdate) 
VALUES (hm_reply_seq.NEXTVAL, 3, 'user2', '桜の季節にまた行きたいです。', SYSDATE);

COMMIT;
