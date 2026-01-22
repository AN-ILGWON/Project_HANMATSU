-- 韓まつ (ハンマツ) データベース作成スクリプト

-- 会員テーブル
CREATE TABLE hm_member (
    userid VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(200) NOT NULL,
    nickname VARCHAR2(50) NOT NULL UNIQUE,
    email VARCHAR2(100) NOT NULL,
    phone VARCHAR2(20),
    last_name_kanji VARCHAR2(50) NOT NULL,
    first_name_kanji VARCHAR2(50) NOT NULL,
    last_name_kana VARCHAR2(50) NOT NULL,
    first_name_kana VARCHAR2(50) NOT NULL,
    role VARCHAR2(20) DEFAULT 'USER',
    profile_img VARCHAR2(500),
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
    instagram VARCHAR2(500),
    map_url VARCHAR2(1000),
    likes NUMBER DEFAULT 0,
    is_recommended CHAR(1) DEFAULT 'N' CHECK (is_recommended IN ('Y', 'N'))
);

CREATE SEQUENCE hm_festival_seq START WITH 1 INCREMENT BY 1;

-- コミュニ티ボードテーブル
CREATE TABLE hm_board (
    bno NUMBER PRIMARY KEY,
    userid VARCHAR2(50) NOT NULL,
    title VARCHAR2(200) NOT NULL,
    content CLOB,
    views NUMBER DEFAULT 0,
    likes NUMBER DEFAULT 0,
    regdate DATE DEFAULT SYSDATE,
    imgfile VARCHAR2(500),
    category VARCHAR2(50),
    FOREIGN KEY (userid) REFERENCES hm_member(userid)
);

CREATE SEQUENCE hm_board_seq START WITH 1 INCREMENT BY 1;

-- カテゴリテーブル
CREATE TABLE hm_category (
    cno NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    type VARCHAR2(50) NOT NULL
);

CREATE SEQUENCE hm_category_seq START WITH 1 INCREMENT BY 1;

-- お気に入り(Wishlist)テーブル
CREATE TABLE hm_wishlist (
    wno NUMBER PRIMARY KEY,
    userid VARCHAR2(50) NOT NULL,
    fno NUMBER NOT NULL,
    wdate DATE DEFAULT SYSDATE,
    FOREIGN KEY (userid) REFERENCES hm_member(userid),
    FOREIGN KEY (fno) REFERENCES hm_festival(fno) ON DELETE CASCADE,
    UNIQUE(userid, fno)
);

CREATE SEQUENCE hm_wishlist_seq START WITH 1 INCREMENT BY 1;

-- ニューステーブル
CREATE TABLE hm_news (
    nno NUMBER PRIMARY KEY,
    title VARCHAR2(200) NOT NULL,
    category VARCHAR2(50),
    content CLOB,
    imgfile VARCHAR2(500),
    link_url VARCHAR2(500),
    regdate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE hm_news_seq START WITH 1 INCREMENT BY 1;

-- バナーテーブル
CREATE TABLE hm_banner (
    bano NUMBER PRIMARY KEY,
    title VARCHAR2(200),
    subtitle VARCHAR2(200),
    imgfile VARCHAR2(500),
    link_url VARCHAR2(500),
    order_no NUMBER DEFAULT 0,
    is_active CHAR(1) DEFAULT 'Y' CHECK (is_active IN ('Y', 'N'))
);

CREATE SEQUENCE hm_banner_seq START WITH 1 INCREMENT BY 1;

-- サイト情報テーブル
CREATE TABLE hm_site_info (
    info_key VARCHAR2(50) PRIMARY KEY,
    title VARCHAR2(200),
    content CLOB,
    updated_date DATE DEFAULT SYSDATE
);

-- 訪問記録テーブル (マイページ用)
CREATE TABLE hm_visited (
    vno NUMBER PRIMARY KEY,
    userid VARCHAR2(50) NOT NULL,
    fno VARCHAR2(50) NOT NULL,
    fname VARCHAR2(200),
    regdate DATE DEFAULT SYSDATE,
    FOREIGN KEY (userid) REFERENCES hm_member(userid)
);

CREATE SEQUENCE hm_visited_seq START WITH 1 INCREMENT BY 1;

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
EXIT;
