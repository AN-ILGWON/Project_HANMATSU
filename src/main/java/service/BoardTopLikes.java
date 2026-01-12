package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;
import model.Command;

public class BoardTopLikes implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// いいねが多い投稿上位5件を取得
		List<BoardDto> topBoards = new BoardDao().getTopLikedBoards(5);
		
		request.setAttribute("topBoards", topBoards);
	}
}

