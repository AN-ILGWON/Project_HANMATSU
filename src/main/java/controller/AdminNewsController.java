package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.NewsDao;
import model.NewsDto;

@WebServlet("/admin/*")
public class AdminNewsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        if(!"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String pathInfo = request.getPathInfo();
        NewsDao dao = new NewsDao();

        if("/newsInsert.do".equals(pathInfo)) {
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String linkUrl = request.getParameter("linkUrl");
            
            NewsDto dto = new NewsDto();
            dto.setTitle(title);
            dto.setCategory(category);
            dto.setLinkUrl(linkUrl);
            
            dao.insertNews(dto);
        } else if("/newsDelete.do".equals(pathInfo)) {
            int nno = Integer.parseInt(request.getParameter("nno"));
            dao.deleteNews(nno);
        }
    }
}
