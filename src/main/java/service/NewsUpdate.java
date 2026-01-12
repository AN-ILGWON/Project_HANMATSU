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

@WebServlet("/admin/newsUpdate.do")
public class NewsUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        if(!"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int nno = Integer.parseInt(request.getParameter("nno"));
        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String linkUrl = request.getParameter("linkUrl");

        NewsDto dto = new NewsDto();
        dto.setNno(nno);
        dto.setTitle(title);
        dto.setCategory(category);
        dto.setLinkUrl(linkUrl);

        NewsDao dao = new NewsDao();
        dao.updateNews(dto);
    }
}
