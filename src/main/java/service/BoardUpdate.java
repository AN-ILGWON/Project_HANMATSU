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

public class BoardUpdate implements Command {

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
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 作成者確認 (管理者は無視)
		BoardDao dao = new BoardDao();
		String writerId = dao.getWriterId(bno);
		String role = (String) session.getAttribute("role");
		
		if(!"ADMIN".equals(role) && !userid.equals(writerId)) {
			response.sendRedirect(request.getContextPath() + "/board/view.do?bno=" + bno);
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		
		// 既存のファイル情報を保持 (新しいファイルがない場合に備えて)
		BoardDto oldDto = dao.getBoardByBno(bno);
		String fileName = oldDto.getImgfile();
		
		// 新しいファイルのアップロード処理
		Part part = request.getPart("imgfile");
		if (part != null && part.getSize() > 0) {
			String originalFileName = getFileName(part);
			if (originalFileName != null && !originalFileName.isEmpty()) {
				// 既存のファイルを削除 (オプション)
				if (fileName != null) {
					File oldFile = new File(util.FileConfig.UPLOAD_PATH, fileName);
					if (oldFile.exists()) oldFile.delete();
				}
				
				fileName = UUID.randomUUID().toString() + "_" + originalFileName;
				String fullPath = util.FileConfig.UPLOAD_PATH + File.separator + fileName;
				
				try (java.io.InputStream is = part.getInputStream();
				     java.io.FileOutputStream fos = new java.io.FileOutputStream(fullPath)) {
					byte[] buffer = new byte[1024];
					int length;
					while ((length = is.read(buffer)) > 0) {
						fos.write(buffer, 0, length);
					}
				}
			}
		}
		
		BoardDto dto = new BoardDto();
		dto.setBno(bno);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImgfile(fileName);
		dto.setCategory(category);
		
		dao.boardUpdate(dto);
		
		response.sendRedirect(request.getContextPath() + "/board/view.do?bno=" + bno);
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

