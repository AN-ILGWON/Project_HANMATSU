package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.ReplyDao;
import model.ReplyDto;
import model.VisitedDao;
import model.VisitedDto;

public class MemberMyPage implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		
		// 1. 自分のコメント一覧を取得
		ReplyDao replyDao = new ReplyDao();
		List<ReplyDto> myReplies = replyDao.getReplyListByUser(userid);
		
		// 2. 最近チェックした祭り一覧を取得
		VisitedDao visitedDao = new VisitedDao();
		List<VisitedDto> recentVisited = visitedDao.getRecentVisited(userid);
		
		// 3. お気に入り(Wishlist)一覧を取得
		model.WishlistDao wishlistDao = model.WishlistDao.getInstance();
		List<model.FestivalDto> myWishlist = wishlistDao.getWishList(userid);
		
		request.setAttribute("myReplies", myReplies);
		request.setAttribute("recentVisited", recentVisited);
		request.setAttribute("myWishlist", myWishlist);
	}
}
