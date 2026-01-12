package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.MemberDao;

public class UserIdCheck implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		MemberDao mdao = new MemberDao();
		int result = mdao.userIdcheck(userid);
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().print(result);
	}
}

