package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FestivalDao;

public class FestivalSetRecommended {
    public void doCommand(HttpServletRequest request, HttpServletResponse response) {
        try {
            int fno = Integer.parseInt(request.getParameter("fno"));
            FestivalDao fDao = new FestivalDao();
            fDao.setRecommendedFestival(fno);
            
            // 成功した場合は1を返す (AJAX用)
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().print("1");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().print("0");
            } catch (Exception ex) {}
        }
    }
}
