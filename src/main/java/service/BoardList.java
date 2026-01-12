package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDao;
import model.BoardDto;
import model.Command;

public class BoardList implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = 10;
		String category = request.getParameter("category");
		
		BoardDao dao = new BoardDao();
		List<BoardDto> list;
		int totalCount;
		
		if (category != null && !category.isEmpty()) {
			list = dao.getBoardListByCategory(category, page, pageSize);
			totalCount = dao.getBoardCountByCategory(category);
		} else {
			list = dao.getBoardList(page, pageSize);
			totalCount = dao.getBoardCount();
		}
		
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		
		request.setAttribute("boardList", list);
		request.setAttribute("currentPage", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("category", category);
	}
}

