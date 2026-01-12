package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.NewsDao;

@WebServlet("/admin/newsDelete.do")
public class NewsDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        if(!"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int nno = Integer.parseInt(request.getParameter("nno"));

        NewsDao dao = new NewsDao();
        dao.deleteNews(nno);
    }
}
