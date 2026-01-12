package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.FestivalDao;
import model.FestivalDto;

public class FestivalList implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = 12; // 3 columns * 4 rows
		
		FestivalDao dao = new FestivalDao();
		List<FestivalDto> festivalList = dao.getFestivalsByPage(page, pageSize);
		int totalCount = dao.getFestivalCount();
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		
		request.setAttribute("festivalList", festivalList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("pageSize", pageSize);
	}
}

