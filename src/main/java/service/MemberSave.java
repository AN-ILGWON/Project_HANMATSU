package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.MemberDao;
import model.MemberDto;
import util.PasswordBcrypt;

public class MemberSave implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String hashpassword = PasswordBcrypt.hashPassword(password);
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String lastNameKanji = request.getParameter("lastNameKanji");
		String firstNameKanji = request.getParameter("firstNameKanji");
		String lastNameKana = request.getParameter("lastNameKana");
		String firstNameKana = request.getParameter("firstNameKana");
		
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
		
		new MemberDao().memberSave(dto);
		
		response.sendRedirect(request.getContextPath() + "/member/login.do");
	}
}

