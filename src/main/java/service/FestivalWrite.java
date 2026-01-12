package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Command;
import model.FestivalDao;
import model.FestivalDto;

public class FestivalWrite implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		
		if(!"ADMIN".equals(role)) {
			response.sendRedirect(request.getContextPath() + "/main.do");
			return;
		}
		
		String region = request.getParameter("region");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String location = request.getParameter("location");
		String homepage = request.getParameter("homepage");
		String mapUrl = request.getParameter("mapUrl");
		
		System.out.println("[INFO] FestivalWrite: Request received. Region=" + region + ", Name=" + name);
		
		String fileName = null;
		
		Part part = request.getPart("imgfile");
		if (part != null && part.getSize() > 0) {
			String originalFileName = getFileName(part);
			if (originalFileName != null && !originalFileName.isEmpty()) {
				fileName = UUID.randomUUID().toString() + "_" + originalFileName;
				try {
					File uploadDir = new File(util.FileConfig.UPLOAD_PATH);
					if (!uploadDir.exists()) {
						System.out.println("[INFO] FestivalWrite: Creating upload directory - " + util.FileConfig.UPLOAD_PATH);
						uploadDir.mkdirs();
					}
					String fullPath = util.FileConfig.UPLOAD_PATH + File.separator + fileName;
					System.out.println("[INFO] FestivalWrite: Saving file to - " + fullPath);
					
					// InputStreamとFileOutputStreamを使用してファイルを確実に保存
					try (InputStream is = part.getInputStream();
					     FileOutputStream fos = new FileOutputStream(fullPath)) {
						byte[] buffer = new byte[1024];
						int length;
						while ((length = is.read(buffer)) > 0) {
							fos.write(buffer, 0, length);
						}
					}
					System.out.println("[INFO] FestivalWrite: File saved successfully.");
				} catch (Exception e) {
					System.out.println("[ERROR] FestivalWrite: File upload error - " + e.getMessage());
					e.printStackTrace();
					fileName = null;
				}
			}
		} else {
			System.out.println("[WARN] FestivalWrite: No image file provided.");
		}
		
		FestivalDto dto = new FestivalDto();
		dto.setRegion(region);
		dto.setName(name);
		dto.setDescription(description);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLocation(location);
		dto.setImgfile(fileName);
		dto.setHomepage(homepage);
		dto.setMapUrl(mapUrl);
		
		int result = new FestivalDao().festivalInsert(dto);
		
		if (result > 0) {
			System.out.println("[INFO] FestivalWrite: Registration successful. Redirecting to main.do");
			response.sendRedirect(request.getContextPath() + "/main.do");
		} else {
			System.out.println("[ERROR] FestivalWrite: Registration failed.");
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println("<script>alert('登録に失敗しました。データベースまたはサーバーログを確認してください。'); history.back();</script>");
		}
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
