# Eclipse 実行ガイド

## 1. プロジェクトのインポート

1. Eclipse を起動
2. `File` → `Import` → `Existing Maven Projects`
3. `Browse` ボタンをクリック
4. `C:\jspstudy26\hanmatsu` フォルダを選択
5. `Finish` をクリック

## 2. 必要なライブラリの追加

`src/main/webapp/WEB-INF/lib/` フォルダに以下の JAR ファイルをコピーしてください：

### 必須ライブラリ
- `ojdbc8.jar` - Oracle JDBC ドライバー
- `jbcrypt-0.4.jar` - パスワード暗号化
- `jstl.jar` - JSTL ライブラリ
- `standard.jar` - JSTL 標準ライブラリ

### ライブラリの場所
- `jslhrd_final_round/src/main/webapp/WEB-INF/lib/` フォルダからコピー可能
- または直接ダウンロード

## 3. プロジェクトの設定

### 3.1 Java バージョンの確認
- プロジェクトを右クリック → `Properties` → `Java Build Path` → `Libraries`
- JavaSE-21 を確認

### 3.2 Project Facets の設定
- プロジェクトを右クリック → `Properties` → `Project Facets`
- 以下の項目にチェック：
  - Java (21)
  - Dynamic Web Project (4.0)
  - JavaScript (1.0)

### 3.3 Deployment Assembly の設定
- プロジェクトを右クリック → `Properties` → `Deployment Assembly`
- `WEB-INF/lib` フォルダが含まれているか確認

## 4. データベースの設定

### 4.1 Oracle DB 接続の確認
`src/main/java/util/DBManager.java` ファイルで DB 接続情報を確認してください：

```java
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "jsl26";  // 自身の DB ユーザー名に変更
String pw = "1234";     // 自身の DB パスワードに変更
```

### 4.2 テーブルの作成
1. Oracle SQL Developer または SQL*Plus を実行
2. `SQL_CREATE_TABLES.sql` ファイルを実行
3. テーブルの作成とサンプルデータの挿入を確認

## 5. Tomcat サーバーの設定

### 5.1 サーバーの追加
1. `Servers` ビューで右クリック → `New` → `Server`
2. `Apache` → `Tomcat v9.0 Server` を選択
3. Tomcat のインストールパスを指定
4. `Next` → プロジェクトをサーバーに追加 → `Finish`

### 5.2 サーバーの起動
1. サーバーを右クリック → `Start`
2. ブラウザで `http://localhost:8080/hanmatsu/main.do` にアクセス

## 6. 実行確認

### 6.1 メインページ
- URL: `http://localhost:8080/hanmatsu/main.do`
- 今年の祝祭リストと人気記事が表示されることを確認

### 6.2 会員登録/ログイン
- URL: `http://localhost:8080/hanmatsu/member/join.do`
- 管理者アカウント: `superadmin` / `admin123`
- 一般アカウント: `user1` / `admin123`

### 6.3 コミュニティ
- URL: `http://localhost:8080/hanmatsu/board/list.do`
- 記事の作成、修正、削除、いいね、コメント機能のテスト

## 7. トラブルシューティング

### 7.1 コンパイルエラー
- プロジェクトを右クリック → `Maven` → `Update Project`
- `Force Update of Snapshots/Releases` にチェック → `OK`

### 7.2 ライブラリのエラー
- `WEB-INF/lib` フォルダにすべての JAR ファイルがあるか確認
- プロジェクトを右クリック → `Build Path` → `Configure Build Path` → `Libraries` を確認

### 7.3 DB 接続エラー
- Oracle DB が実行中か確認
- `DBManager.java` の接続情報を確認
- ファイアウォール設定を確認

### 7.4 404 エラー
- サーバーの `server.xml` で Context パスを確認
- `web.xml` の welcome-file 設定を確認

## 8. デバッグ

### 8.1 ログの確認
- Eclipse の Console ウィンドウでエラーメッセージを確認
- サーバーログを確認 (Servers → Tomcat → logs)

### 8.2 ブレークポイントの設定
- Java ファイルで行番号の左側をクリック
- Debug モードでサーバーを起動
- ブラウザで該当機能を実行

## 補足事項

- プロジェクトパス: `C:\jspstudy26\hanmatsu`
- テーブルプレフィックス: `hm_` (例: `hm_member`, `hm_board`)
- デフォルトポート: 8080
- コンテキストパス: `/hanmatsu`
