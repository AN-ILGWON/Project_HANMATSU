package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardDelete;
import service.BoardList;
import service.BoardUpdate;
import service.BoardView;
import service.BoardWrite;
import service.LikeToggle;
import service.ReplyDelete;
import service.ReplyUpdate;
import service.ReplyWrite;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10,      // 10MB
	maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getPathInfo();
		System.out.println("Board URL : " + uri);
		
		if (uri == null) {
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String servletPath = request.getServletPath();
			uri = requestURI.substring(contextPath.length() + servletPath.length());
			System.out.println("Board URL (fallback) : " + uri);
		}
		
		String page = null;
		
		switch(uri) {
		case "/list.do":
			new BoardList().doCommand(request, response);
			page = "/WEB-INF/views/board/board_list.jsp";
			break;
		case "/write.do":
			page = "/WEB-INF/views/board/board_write.jsp";
			break;
		case "/writepro.do":
			new BoardWrite().doCommand(request, response);
			break;
		case "/view.do":
			new BoardView().doCommand(request, response);
			page = "/WEB-INF/views/board/board_view.jsp";
			break;
		case "/update.do":
			new BoardView().doCommand(request, response);
			page = "/WEB-INF/views/board/board_update.jsp";
			break;
		case "/updatepro.do":
			new BoardUpdate().doCommand(request, response);
			break;
		case "/delete.do":
			new BoardDelete().doCommand(request, response);
			break;
		case "/like.do":
			new LikeToggle().doCommand(request, response);
			break;
		case "/replyInsert.do":
			new ReplyWrite().doCommand(request, response);
			break;
		case "/replyUpdate.do":
			new ReplyUpdate().doCommand(request, response);
			break;
		case "/replyDelete.do":
			new ReplyDelete().doCommand(request, response);
			break;
		default:
			System.out.println(" * BoardCon : 無効なリクエストです。");
			break;
		}
		
		if(page != null) {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}

