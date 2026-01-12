package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.LikeDao;

public class LikeToggle implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if(userid == null) {
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print("login");
			return;
		}
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		LikeDao likeDao = new LikeDao();
		boolean isLiked = likeDao.isLiked(bno, userid);
		
		if(isLiked) {
			// いいね取り消し
			likeDao.likeDelete(bno, userid);
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print("unliked");
		} else {
			// いいね追加
			likeDao.likeInsert(bno, userid);
			response.setContentType("text/plain; charset=utf-8");
			response.getWriter().print("liked");
		}
	}
}

