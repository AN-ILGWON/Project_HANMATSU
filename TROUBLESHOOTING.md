# トラブルシューティングガイド

## インデックスコントローラーが実行されない場合

### 1. URLの確認
サーバーポートが **8088** なので、以下のURLでアクセスしてください：
- `http://localhost:8088/hanmatsu/main.do`
- または `http://localhost:8088/hanmatsu/` (welcome-fileにより自動リダイレクト)

### 2. コンテキストパスの確認
Eclipseでプロジェクトをサーバーに追加する際、コンテキストパスが `/hanmatsu` に設定されているか確認してください。

**確認方法:**
1. Serversビューでプロジェクトを右クリック
2. Properties → Web Project Settings
3. Context root が `/hanmatsu` であることを確認

### 3. コンパイルの確認
プロジェクトが正しくコンパイルされているか確認してください：
1. プロジェクトを右クリック → `Clean...`
2. プロジェクトを右クリック → `Build Project`
3. Consoleでコンパイルエラーを確認

### 4. ライブラリの確認
`WEB-INF/lib` フォルダに以下のファイルがあるか確認してください：
- `ojdbc8.jar`
- `jbcrypt-0.4.jar`
- `jstl.jar`
- `standard.jar`

### 5. DB接続の確認
`DBManager.java` でDB接続情報を確認してください：
```java
String user = "jsl26";  // 自身のDBユーザー名
String pw = "1234";     // 自身のDBパスワード
```

**DB接続テスト:**
- Oracle DBが実行中か確認
- SQL Developerで接続テスト

### 6. エラーログの確認
EclipseのConsoleウィンドウで以下を確認してください：
- Javaコンパイルエラー
- サーバー起動時のエラー
- ランタイムエラー

**主な確認事項:**
- `ClassNotFoundException` - ライブラリの不足
- `SQLException` - DB接続の問題
- `NullPointerException` - データが存在しない場合

### 7. サーバーの再起動
1. サーバーを停止
2. プロジェクトをClean
3. サーバーを再起動

### 8. ブラウザキャッシュの削除
ブラウザで `Ctrl + Shift + Delete` を押し、キャッシュを削除してから再度アクセスしてください。

## 一般的なエラーと解決方法

### 404 エラー
- URLパスの確認
- コンテキストパスの確認
- web.xmlの設定確認

### 500 エラー (Internal Server Error)
- Consoleでエラーメッセージを確認
- DB接続の確認
- データがない場合のnullチェック追加

### JSPコンパイルエラー
- JSTLライブラリの確認
- JSP構文の確認
- エンコーディングの問題確認 (UTF-8)

### DB接続エラー
- Oracle DBの実行確認
- 接続情報の確認
- ファイアウォール設定の確認

## デバッグのヒント

### 1. System.out.println の追加
コントローラーにログを追加：
```java
System.out.println("IndexController 実行中");
```

### 2. ブレークポイントの設定
1. `IndexController.java` の `doGet` メソッドにブレークポイントを設定
2. Debugモードでサーバーを実行
3. ブラウザからアクセス
4. Eclipseで変数の値を確認

### 3. サーバーログの確認
`Servers` → `Tomcat v9.0` → `logs` フォルダで詳細ログを確認

## チェックリスト

- [ ] プロジェクトがサーバーに追加されている
- [ ] サーバーが正常に起動している
- [ ] ライブラリが `WEB-INF/lib` に存在する
- [ ] DB接続情報が正しい
- [ ] テーブルが作成されている
- [ ] サンプルデータが挿入されている
- [ ] URLが正しい (ポート 8088)
- [ ] コンテキストパスが `/hanmatsu` である
