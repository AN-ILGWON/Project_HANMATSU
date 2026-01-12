# 韓まつ (ハンマツ) - 韓国の地域祭り紹介サイト

## プロジェクト概要

韓国の地域祭りを紹介し、コミュニティ機能を備えた日本語ウェブサイトです。

## 主な機能

### 1. 祭り紹介
- 今年開催される祭りの一覧表示
- 祭りの詳細情報（地域、期間、場所など）

### 2. コミュニティ
- 投稿機能（ログイン必須）
- いいね機能
- コメント機能
- 投稿の編集・削除（本人のみ）

### 3. 会員管理
- 会員登録
- ログイン/ログアウト
- ID重複チェック

## 技術スタック

- **言語**: Java 21
- **フレームワーク**: JSP/Servlet
- **データベース**: Oracle Database
- **ビルドツール**: Maven
- **パターン**: MVC (Model-View-Controller)

## プロジェクト構造

```
hanmatsu/
├── src/main/java/
│   ├── controller/     # コントローラー（リクエスト処理）
│   ├── model/          # モデル（DAO, DTO）
│   ├── service/        # サービス（ビジネスロジック）
│   └── util/           # ユーティリティ
├── src/main/webapp/
│   ├── board/          # コミュニティページ
│   ├── member/         # 会員ページ
│   ├── css/            # スタイルシート
│   ├── js/             # JavaScript
│   └── index.jsp       # メインページ
└── SQL_CREATE_TABLES.sql  # データベース作成スクリプト
```

## セットアップ

### 1. データベース設定

1. Oracle Databaseに接続
2. `SQL_CREATE_TABLES.sql`を実行してテーブルを作成

### 2. データベース接続設定

`src/main/java/util/DBManager.java`の接続情報を修正：

```java
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "your_username";
String pw = "your_password";
```

### 3. ライブラリ

以下のJARファイルを`WEB-INF/lib/`に配置：
- `ojdbc8.jar` (Oracle JDBC Driver)
- `jbcrypt-0.4.jar` (パスワード暗号化)
- JSTLライブラリ

### 4. 実行

Tomcatサーバーでプロジェクトを実行

## URL構造

- `/main.do` - メインページ（祭り紹介 + 人気投稿）
- `/board/list.do` - コミュニティ一覧
- `/board/view.do?bno=1` - 投稿詳細
- `/board/write.do` - 投稿作成
- `/member/login.do` - ログイン
- `/member/join.do` - 会員登録

## データベーステーブル

- `member` - 会員情報
- `festival` - 祭り情報
- `board` - コミュニティ投稿
- `board_like` - いいね情報
- `reply` - コメント

## 開発者向け

### コマンドパターン

各サービスクラスは`Command`インターフェースを実装し、`doCommand`メソッドで処理を行います。

### セッション管理

ログイン情報は`HttpSession`に保存：
- `sessionScope.userid` - ユーザーID
- `sessionScope.username` - ユーザー名

## ライセンス

Copyright © 2026 All Right Reserved.

