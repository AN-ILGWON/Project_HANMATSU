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
		
		String pageStr = request.getParameter("page");
		String status = request.getParameter("status");
		String keyword = request.getParameter("keyword");
		System.out.println("[FestivalList] params - page: " + pageStr + ", status: " + status + ", keyword: " + keyword);
		
		int page = 1;
		try {
			if (pageStr != null && !pageStr.isEmpty()) {
				page = Integer.parseInt(pageStr);
			}
		} catch (NumberFormatException e) {
			System.err.println("[FestivalList] Invalid page number: " + pageStr);
			page = 1;
		}
		
		int pageSize = 12; // 3 columns * 4 rows
		FestivalDao dao = new FestivalDao();
		
		System.out.println("[FestivalList] Calling getFestivalsByPage...");
		List<FestivalDto> festivalList = dao.getFestivalsByPage(page, pageSize, status, keyword);
		if (festivalList == null) {
			festivalList = new java.util.ArrayList<>();
		}
		System.out.println("[FestivalList] festivalList size: " + festivalList.size());
		
		System.out.println("[FestivalList] Calling getFestivalCount...");
		int totalCount = dao.getFestivalCount(status, keyword);
		System.out.println("[FestivalList] totalCount: " + totalCount);
		
		int totalPages = (totalCount > 0) ? (int) Math.ceil((double) totalCount / pageSize) : 1;
		
		request.setAttribute("festivalList", festivalList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("status", status);
		request.setAttribute("keyword", keyword);
	}
}

