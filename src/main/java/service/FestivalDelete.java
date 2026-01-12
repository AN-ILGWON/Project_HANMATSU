package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.FestivalDao;

public class FestivalDelete implements Command {

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
		
		new FestivalDao().festivalDelete(fno);
		
		response.sendRedirect(request.getContextPath() + "/main.do");
	}
}
