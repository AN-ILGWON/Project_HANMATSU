package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.MemberDao;

public class MemberDelete implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		
		MemberDao dao = new MemberDao();
		int result = dao.deleteMember(userid);
		
		if (result > 0) {
			session.invalidate(); // セッションを破棄 (ログアウト)
			response.getWriter().write("<script>alert('退会処理が完了しました。ご利用ありがとうございました。'); location.href='" + request.getContextPath() + "/main.do';</script>");
		} else {
			response.getWriter().write("<script>alert('退会処理に失敗しました。'); history.back();</script>");
		}
	}
}
