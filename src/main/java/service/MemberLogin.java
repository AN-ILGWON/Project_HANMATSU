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
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			
			System.out.println("Login attempt for userid: " + userid);
			
			MemberDao mdao = new MemberDao();
			System.out.println("DAO instance created");
			
			MemberDto dto = mdao.searchUserId(userid);
			System.out.println("DAO search finished. dto is null? " + (dto == null));
			
			if(dto != null) {
				System.out.println("User found: " + dto.getUserid());
				System.out.println("Hashed password from DB: " + dto.getPassword());
				
				boolean isMatch = false;
				try {
					isMatch = PasswordBcrypt.checkPassword(password, dto.getPassword());
					System.out.println("Password check finished. Match? " + isMatch);
				} catch (Exception e) {
					System.err.println("Error during PasswordBcrypt.checkPassword: " + e.getMessage());
					e.printStackTrace();
					throw e;
				}

				if (isMatch) {
					HttpSession session = request.getSession();
					session.setAttribute("userid", dto.getUserid());
					
					String displayName = dto.getNickname();
					if ("superadmin".equals(dto.getUserid())) {
						displayName = "運営事務局";
					}
					
					session.setAttribute("username", displayName);
					session.setAttribute("nickname", displayName);
					session.setAttribute("role", dto.getRole());
					session.setAttribute("profileImg", dto.getProfileImg());
					
					response.setContentType("text/plain; charset=utf-8");
					response.getWriter().print("success");
					return;
				} else {
					System.out.println("Password mismatch for user: " + userid);
				}
			} else {
				System.out.println("User not found: " + userid);
			}
			
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print("fail");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().print("Error: " + e.getMessage());
		}
	}
}

