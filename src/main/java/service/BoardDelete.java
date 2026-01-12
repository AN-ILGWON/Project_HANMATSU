package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDao;
import model.Command;

public class BoardDelete implements Command {

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
		String writerId = new BoardDao().getWriterId(bno);
		String role = (String) session.getAttribute("role");
		
		if(!"ADMIN".equals(role) && !userid.equals(writerId)) {
			response.sendRedirect(request.getContextPath() + "/board/view.do?bno=" + bno);
			return;
		}
		
		new BoardDao().boardDelete(bno);
		
		response.sendRedirect(request.getContextPath() + "/board/list.do");
	}
}

