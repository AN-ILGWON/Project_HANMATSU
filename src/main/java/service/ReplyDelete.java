package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.ReplyDao;

public class ReplyDelete implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if(userid == null) {
			response.sendRedirect("/member/login.do");
			return;
		}
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 作成者確認 (管理者は無視)
		String writerId = new ReplyDao().getWriterId(rno);
		String role = (String) session.getAttribute("role");
		
		if(!"ADMIN".equals(role) && !userid.equals(writerId)) {
			response.sendRedirect(request.getContextPath() + "/board/view.do?bno=" + bno);
			return;
		}
		
		new ReplyDao().replyDelete(rno);
		
		response.sendRedirect("/board/view.do?bno=" + bno);
	}
}

