package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewsDao;
import model.NewsDto;

public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int pageSize = 9; // News grid looks good with 3x3
        
        String pageStr = request.getParameter("page");
        if(pageStr != null && !pageStr.isEmpty()) {
            page = Integer.parseInt(pageStr);
        }
        
        NewsDao nDao = new NewsDao();
        List<NewsDto> list = nDao.getNewsList(page, pageSize);
        int totalCount = nDao.getNewsCount();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
        request.setAttribute("newsList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCount", totalCount);
        
        request.getRequestDispatcher("/WEB-INF/views/news/news_list.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
