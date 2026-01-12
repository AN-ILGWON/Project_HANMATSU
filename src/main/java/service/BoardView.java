package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;
import model.Command;
import model.LikeDao;
import model.ReplyDao;
import model.ReplyDto;

public class BoardView implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 閲覧数を増やす
		new BoardDao().viewCount(bno);
		
		// 投稿を取得
		BoardDto dto = new BoardDao().getBoardByBno(bno);
		
		// コメント一覧を取得
		List<ReplyDto> replyList = new ReplyDao().getReplyList(bno);
		
		// いいね済みか確認 (ログインしている場合)
		String userid = (String) request.getSession().getAttribute("userid");
		boolean isLiked = false;
		if(userid != null) {
			isLiked = new LikeDao().isLiked(bno, userid);
		}
		
		request.setAttribute("board", dto);
		request.setAttribute("replyList", replyList);
		request.setAttribute("isLiked", isLiked);
	}
}

