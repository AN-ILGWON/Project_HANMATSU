package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Command;
import model.WishlistDao;

public class FestivalWishToggle implements Command {
    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        
        if (userid == null) {
            response.getWriter().write("login");
            return;
        }

        int fno = Integer.parseInt(request.getParameter("fno"));
        WishlistDao dao = WishlistDao.getInstance();
        
        boolean isWished = dao.isWished(userid, fno);
        int result;
        
        if (isWished) {
            result = dao.deleteWish(userid, fno);
            if (result > 0) response.getWriter().write("deleted");
        } else {
            result = dao.insertWish(userid, fno);
            if (result > 0) response.getWriter().write("inserted");
        }
    }
}
