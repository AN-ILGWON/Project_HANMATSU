package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.MemberDao;
import model.MemberDto;
import util.PasswordBcrypt;

public class MemberLogin implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		MemberDto dto = new MemberDao().searchUserId(userid);
		
		if(dto != null && PasswordBcrypt.checkPassword(password, dto.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", dto.getUserid());
			
			String displayName = dto.getNickname();
			if ("superadmin".equals(dto.getUserid())) {
				displayName = "運営事務局";
			}
			
			session.setAttribute("username", displayName);
			session.setAttribute("nickname", displayName);
			session.setAttribute("role", dto.getRole());
			
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print("success");
			return;
		}
		
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().print("fail");
	}
}

