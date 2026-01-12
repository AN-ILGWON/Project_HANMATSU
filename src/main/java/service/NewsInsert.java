package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NewsDao;
import model.NewsDto;

@WebServlet("/admin/newsInsert.do")
public class NewsInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        if(!"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String linkUrl = request.getParameter("linkUrl");

        NewsDto dto = new NewsDto();
        dto.setTitle(title);
        dto.setCategory(category);
        dto.setLinkUrl(linkUrl);
        dto.setImgfile("https://picsum.photos/400/250?random=" + (int)(Math.random() * 100)); // Default random image

        NewsDao dao = new NewsDao();
        dao.insertNews(dto);
    }
}
