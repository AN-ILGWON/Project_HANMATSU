-- 韓まつ (ハンマツ) データベース作成スクリプト

-- 会員テーブル
CREATE TABLE hm_member (
    userid VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(200) NOT NULL,
    nickname VARCHAR2(50) NOT NULL UNIQUE, -- ニックネームにUNIQUE制約を追加
    email VARCHAR2(100) NOT NULL,
    phone VARCHAR2(20),
    last_name_kanji VARCHAR2(50) NOT NULL,
    first_name_kanji VARCHAR2(50) NOT NULL,
    last_name_kana VARCHAR2(50) NOT NULL,
    first_name_kana VARCHAR2(50) NOT NULL,
    role VARCHAR2(20) DEFAULT 'USER', -- 権限 (USER, ADMIN)
    regdate DATE DEFAULT SYSDATE
);

-- 祭りテーブル
CREATE TABLE hm_festival (
    fno NUMBER PRIMARY KEY,
    region VARCHAR2(50) NOT NULL,
    name VARCHAR2(200) NOT NULL,
    description CLOB,
    start_date DATE,
    end_date DATE,
    location VARCHAR2(200),
    imgfile VARCHAR2(500),
    views NUMBER DEFAULT 0,
    regdate DATE DEFAULT SYSDATE,
    homepage VARCHAR2(500),
    map_url VARCHAR2(1000)
);

CREATE SEQUENCE hm_festival_seq START WITH 1 INCREMENT BY 1;

-- コミュニティボードテーブル
CREATE TABLE hm_board (
    bno NUMBER PRIMARY KEY,
    userid VARCHAR2(50) NOT NULL,
    title VARCHAR2(200) NOT NULL,
    content CLOB,
    views NUMBER DEFAULT 0,
    likes NUMBER DEFAULT 0,
    regdate DATE DEFAULT SYSDATE,
    imgfile VARCHAR2(500),
    FOREIGN KEY (userid) REFERENCES hm_member(userid)
);

CREATE SEQUENCE hm_board_seq START WITH 1 INCREMENT BY 1;

-- 訪問記録テーブル (マイページ用)
CREATE SEQUENCE hm_visited_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE hm_visited (
    vno NUMBER PRIMARY KEY,
    userid VARCHAR2(50) NOT NULL,
    fno VARCHAR2(50) NOT NULL, -- 祭り番号またはTourAPI contentId
    fname VARCHAR2(200),       -- 祭り名 (APIデータの場合の名前保存用)
    regdate DATE DEFAULT SYSDATE,
    FOREIGN KEY (userid) REFERENCES hm_member(userid)
);

-- いいねテーブル
CREATE TABLE hm_board_like (
    like_no NUMBER PRIMARY KEY,
    bno NUMBER NOT NULL,
    userid VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    FOREIGN KEY (bno) REFERENCES hm_board(bno) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES hm_member(userid),
    UNIQUE(bno, userid)
);

CREATE SEQUENCE hm_like_seq START WITH 1 INCREMENT BY 1;

-- コメントテーブル
CREATE TABLE hm_reply (
    rno NUMBER PRIMARY KEY,
    bno NUMBER NOT NULL,
    userid VARCHAR2(50) NOT NULL,
    content VARCHAR2(1000) NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    FOREIGN KEY (bno) REFERENCES hm_board(bno) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES hm_member(userid)
);

CREATE SEQUENCE hm_reply_seq START WITH 1 INCREMENT BY 1;

COMMIT;
