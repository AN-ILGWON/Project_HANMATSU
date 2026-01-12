package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.FestivalDao;
import model.FestivalDto;
import model.VisitedDao;

public class FestivalView implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String fnoStr = request.getParameter("fno");
		if(fnoStr == null || fnoStr.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/main.do");
			return;
		}
		
		int fno = Integer.parseInt(fnoStr);
		
		FestivalDao dao = new FestivalDao();
		
		// 閲覧数を増やす
		dao.viewCount(fno);
		
		// お祭り情報を取得
		FestivalDto dto = dao.getFestivalByFno(fno);
		
		// 訪問記録を保存
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		if (userid != null && dto != null) {
			new VisitedDao().insertVisited(userid, String.valueOf(fno), dto.getName());
			// 찜 여부 확인
			boolean isWished = model.WishlistDao.getInstance().isWished(userid, fno);
			request.setAttribute("isWished", isWished);
		}
		
		request.setAttribute("festival", dto);
	}
}
