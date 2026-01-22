package service;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Command;
import model.MemberDao;
import model.MemberDto;
import util.PasswordBcrypt;

public class MemberSave implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			// ファイルアップロード設定
			String savePath = request.getServletContext().getRealPath("upload/profile");
			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			int maxSize = 5 * 1024 * 1024; // 5MB
			String encoding = "UTF-8";
			
			MultipartRequest multi = null;
			try {
				multi = new MultipartRequest(
					request, 
					savePath, 
					maxSize, 
					encoding, 
					new DefaultFileRenamePolicy()
				);
			} catch (Exception e) {
				System.err.println("MultipartRequest error: " + e.getMessage());
				e.printStackTrace();
				throw e;
			}
			
			String userid = multi.getParameter("userid");
			String password = multi.getParameter("password");
			String hashpassword = PasswordBcrypt.hashPassword(password);
			String nickname = multi.getParameter("nickname");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			String lastNameKanji = multi.getParameter("lastNameKanji");
			String firstNameKanji = multi.getParameter("firstNameKanji");
			String lastNameKana = multi.getParameter("lastNameKana");
			String firstNameKana = multi.getParameter("firstNameKana");
			String profileImg = multi.getFilesystemName("profileImg");
			
			MemberDto dto = new MemberDto();
			dto.setUserid(userid);
			dto.setPassword(hashpassword);
			dto.setNickname(nickname);
			dto.setEmail(email);
			dto.setPhone(phone != null ? phone : "");
			dto.setLastNameKanji(lastNameKanji);
			dto.setFirstNameKanji(firstNameKanji);
			dto.setLastNameKana(lastNameKana);
			dto.setFirstNameKana(firstNameKana);
			dto.setProfileImg(profileImg);
			
			int result = new MemberDao().memberSave(dto);
			
			if (result > 0) {
				System.out.println("Member saved: " + userid);
				response.sendRedirect(request.getContextPath() + "/member/login.do");
			} else {
				System.err.println("Member save failed: " + userid);
				response.sendRedirect(request.getContextPath() + "/member/join.do?error=save_failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/member/join.do?error=system_error");
		}
	}
}

