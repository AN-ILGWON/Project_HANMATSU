package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberLogin;
import service.MemberLogout;
import service.MemberMyPage;
import service.MemberSave;
import service.MemberUpdate;
import service.MemberDelete;
import service.UserIdCheck;
import service.NicknameCheck;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String uri = request.getPathInfo();

			if (uri == null) {
				String requestURI = request.getRequestURI();
				String contextPath = request.getContextPath();
				String servletPath = request.getServletPath();
				uri = requestURI.substring(contextPath.length() + servletPath.length());
			}

			System.out.println("[MemberController] URI: " + uri);

			String page = null;

			switch (uri) {
			case "/login.do":
				page = "/WEB-INF/views/member/member_login.jsp";
				break;
			case "/loginpro.do":
				new MemberLogin().doCommand(request, response);
				break;
			case "/join.do":
				page = "/WEB-INF/views/member/member_join.jsp";
				break;
			case "/userIdCheck.do":
				new UserIdCheck().doCommand(request, response);
				break;
			case "/nicknameCheck.do":
				new NicknameCheck().doCommand(request, response);
				break;
			case "/memberSave.do":
				new MemberSave().doCommand(request, response);
				break;
			case "/logout.do":
				new MemberLogout().doCommand(request, response);
				break;
			case "/mypage.do":
				new MemberMyPage().doCommand(request, response);
				page = "/WEB-INF/views/member/member_mypage.jsp";
				break;
			case "/update.do":
				new MemberUpdate().doCommand(request, response);
				page = "/WEB-INF/views/member/member_update.jsp";
				break;
			case "/updatepro.do":
				new MemberUpdate().doCommand(request, response);
				break;
			case "/delete.do":
				new MemberDelete().doCommand(request, response);
				break;
			default:
				System.out.println(" * MemberCon : 無効なリクエストです。 uri=" + uri);
				break;
			}
			
			if (page != null) {
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			System.err.println("[MemberController] Error: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
