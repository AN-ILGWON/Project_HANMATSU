package util;

import java.io.File;

public class FileConfig {
    // ファイルを永久に保管する外部の絶対パス
    // サーバーが再デプロイされても、このパスは消えません。
    public static final String UPLOAD_PATH = "C:/hanmatsu_uploads";

    static {
        // フォルダがない場合は自動的に作成
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }
}
