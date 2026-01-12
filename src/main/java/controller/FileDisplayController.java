package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileConfig;

public class FileDisplayController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("name");
        if (fileName == null || fileName.isEmpty()) return;

        // 外部の絶対パスからファイルオブジェクトを作成
        File file = new File(FileConfig.UPLOAD_PATH, fileName);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ファイルのMIMEタイプを把握 (image/jpeg, image/png など)
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLength((int) file.length());

        // ファイルを読み込んでブラウザに転送
        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
