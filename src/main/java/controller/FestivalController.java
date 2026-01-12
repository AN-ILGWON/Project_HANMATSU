package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FestivalView;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10,      // 10MB
	maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class FestivalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FestivalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		String viewPage = null;
		
		if(command.equals("/festival/view.do")) {
			new FestivalView().doCommand(request, response);
			viewPage = "/WEB-INF/views/festival/festival_view.jsp";
		} else if(command.equals("/festival/list.do")) {
			new service.FestivalList().doCommand(request, response);
			viewPage = "/WEB-INF/views/festival/festival_list.jsp";
		} else if(command.equals("/festival/write.do")) {
			// 権限チェック
			javax.servlet.http.HttpSession session = request.getSession();
			String role = (String) session.getAttribute("role");
			if(!"ADMIN".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/main.do");
				return;
			}
			viewPage = "/WEB-INF/views/festival/festival_write.jsp";
		} else if(command.equals("/festival/writepro.do")) {
			new service.FestivalWrite().doCommand(request, response);
			return; // redirect internally
		} else if(command.equals("/festival/update.do")) {
			new FestivalView().doCommand(request, response);
			viewPage = "/WEB-INF/views/festival/festival_update.jsp";
		} else if(command.equals("/festival/updatepro.do")) {
			new service.FestivalUpdate().doCommand(request, response);
			return; // redirect internally
		} else if(command.equals("/festival/delete.do")) {
			new service.FestivalDelete().doCommand(request, response);
			return; // redirect internally
		} else if(command.equals("/festival/apiList.do")) {
			new service.TourFestivalList().doCommand(request, response);
			viewPage = "/WEB-INF/views/festival/festival_api_list.jsp";
		} else if(command.equals("/festival/apiView.do")) {
			new service.TourFestivalView().doCommand(request, response);
			viewPage = "/WEB-INF/views/festival/festival_api_view.jsp";
		} else if(command.equals("/festival/wish.do")) {
			new service.FestivalWishToggle().doCommand(request, response);
			return;
		}
		
		if(viewPage != null) {
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
