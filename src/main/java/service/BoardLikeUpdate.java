package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BoardDao;

@WebServlet("/board/likeUpdate.do")
public class BoardLikeUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        
        if(!"ADMIN".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        int bno = Integer.parseInt(request.getParameter("bno"));
        int likes = Integer.parseInt(request.getParameter("likes"));

        BoardDao dao = new BoardDao();
        dao.updateLikeCount(bno, likes);
    }
}
