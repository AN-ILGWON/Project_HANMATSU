package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewsDao;
import model.NewsDto;

@WebServlet({"/news/list.do", "/news/view.do"})
public class NewsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        
        if (servletPath.equals("/news/list.do")) {
            doList(request, response);
        } else if (servletPath.equals("/news/view.do")) {
            doView(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void doView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nnoStr = request.getParameter("nno");
        if (nnoStr == null || nnoStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/news/list.do");
            return;
        }

        int nno = Integer.parseInt(nnoStr);
        NewsDao nDao = new NewsDao();
        NewsDto dto = nDao.getNewsByNno(nno);

        if (dto == null) {
            response.sendRedirect(request.getContextPath() + "/news/list.do");
            return;
        }

        request.setAttribute("news", dto);
        request.getRequestDispatcher("/WEB-INF/views/news/news_view.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
