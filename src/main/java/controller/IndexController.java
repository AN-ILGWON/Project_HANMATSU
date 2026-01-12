package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FestivalDao;
import model.FestivalDto;
import model.BoardDao;
import model.BoardDto;
import model.NewsDao;
import model.NewsDto;
import java.util.List;

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 今年の祭りリスト (DB)
			FestivalDao fDao = new FestivalDao();
			List<FestivalDto> festivalList = fDao.getThisYearFestivals();
			request.setAttribute("festivalList", festivalList);
			
			// いいねが多い投稿
			BoardDao bDao = new BoardDao();
			List<BoardDto> topBoards = bDao.getTopLikedBoards(4);
			request.setAttribute("topBoards", topBoards);
			
			// 旅行ニュースの取得
			NewsDao nDao = new NewsDao();
			request.setAttribute("newsList", nDao.getNewsList());
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index_view.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

