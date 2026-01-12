package service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Command;
import model.FestivalDao;
import model.FestivalDto;

public class FestivalUpdate implements Command {

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
		
		int fno = Integer.parseInt(request.getParameter("fno"));
		String region = request.getParameter("region");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String location = request.getParameter("location");
		String homepage = request.getParameter("homepage");
		String mapUrl = request.getParameter("mapUrl");
		
		FestivalDao dao = new FestivalDao();
		FestivalDto oldDto = dao.getFestivalByFno(fno);
		String fileName = oldDto.getImgfile();
		
		Part part = request.getPart("imgfile");
		if (part != null && part.getSize() > 0) {
			String originalFileName = getFileName(part);
			if (originalFileName != null && !originalFileName.isEmpty()) {
				// 既存ファイルを削除
				if (fileName != null) {
					File oldFile = new File(util.FileConfig.UPLOAD_PATH, fileName);
					if (oldFile.exists()) oldFile.delete();
				}
				
				fileName = UUID.randomUUID().toString() + "_" + originalFileName;
				try {
					File uploadDir = new File(util.FileConfig.UPLOAD_PATH);
					if (!uploadDir.exists()) uploadDir.mkdirs();
					String fullPath = util.FileConfig.UPLOAD_PATH + File.separator + fileName;
					
					// InputStreamとFileOutputStreamを使用してファイルを確実に保存
					try (java.io.InputStream is = part.getInputStream();
					     java.io.FileOutputStream fos = new java.io.FileOutputStream(fullPath)) {
						byte[] buffer = new byte[1024];
						int length;
						while ((length = is.read(buffer)) > 0) {
							fos.write(buffer, 0, length);
						}
					}
				} catch (Exception e) {
					System.out.println("[ERROR] FestivalUpdate: File upload error - " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		FestivalDto dto = new FestivalDto();
		dto.setFno(fno);
		dto.setRegion(region);
		dto.setName(name);
		dto.setDescription(description);
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		dto.setLocation(location);
		dto.setImgfile(fileName);
		dto.setHomepage(homepage);
		dto.setMapUrl(mapUrl);
		
		dao.festivalUpdate(dto);
		
		response.sendRedirect(request.getContextPath() + "/festival/view.do?fno=" + fno);
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
