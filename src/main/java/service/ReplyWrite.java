package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.ReplyDao;
import model.ReplyDto;

public class ReplyWrite implements Command {

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
		String content = request.getParameter("content");
		
		ReplyDto dto = new ReplyDto();
		dto.setBno(bno);
		dto.setUserid(userid);
		dto.setContent(content);
		
		new ReplyDao().replyInsert(dto);
		
		response.sendRedirect(request.getContextPath() + "/board/view.do?bno=" + bno);
	}
}

