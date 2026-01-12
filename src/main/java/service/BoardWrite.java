package service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.BoardDao;
import model.BoardDto;
import model.Command;

public class BoardWrite implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		
		// ファイルアップロード処理 (外部の永久保存先を利用)
		String uploadPath = util.FileConfig.UPLOAD_PATH;
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		String fileName = null;
		
		Part part = request.getPart("imgfile");
		if (part != null && part.getSize() > 0) {
			String originalFileName = getFileName(part);
			if (originalFileName != null && !originalFileName.isEmpty()) {
				fileName = UUID.randomUUID().toString() + "_" + originalFileName;
				try {
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						System.out.println("[INFO] BoardWrite: Creating upload directory - " + uploadPath);
						uploadDir.mkdirs();
					}
					String fullPath = uploadPath + File.separator + fileName;
					System.out.println("[INFO] BoardWrite: Saving file to - " + fullPath);
					
					// InputStreamとFileOutputStreamを使用してファイルを確実に保存
					try (java.io.InputStream is = part.getInputStream();
					     java.io.FileOutputStream fos = new java.io.FileOutputStream(fullPath)) {
						byte[] buffer = new byte[1024];
						int length;
						while ((length = is.read(buffer)) > 0) {
							fos.write(buffer, 0, length);
						}
					}
					System.out.println("[INFO] BoardWrite: File saved successfully.");
				} catch (Exception e) {
					System.out.println("[ERROR] BoardWrite: File upload error - " + e.getMessage());
					e.printStackTrace();
					fileName = null;
				}
			}
		}
		
		BoardDto dto = new BoardDto();
		dto.setUserid(userid);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImgfile(fileName);
		dto.setCategory(category);
		
		new BoardDao().boardInsert(dto);
		
		response.sendRedirect(request.getContextPath() + "/board/list.do");
	}
	
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		if (contentDisp == null) return null;
		
		for (String content : contentDisp.split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
				// ブラウザによってフルパスが含まれる場合があるため、ファイル名のみを抽出 (\ または / 基準)
				int lastSlash = Math.max(fileName.lastIndexOf("/"), fileName.lastIndexOf("\\"));
				if (lastSlash >= 0) {
					return fileName.substring(lastSlash + 1);
				}
				return fileName;
			}
		}
		return null;
	}
}

