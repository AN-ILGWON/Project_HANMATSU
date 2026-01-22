package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/admin/*")
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10,      // 10MB
	maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession();
			String role = (String) session.getAttribute("role");
			
			if (!"ADMIN".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/main.do");
				return;
			}

			String uri = request.getPathInfo();
			System.out.println("[AdminController] doAction URI: " + uri);
			System.out.println("[AdminController] ServletPath: " + request.getServletPath());
			System.out.println("[AdminController] ContextPath: " + request.getContextPath());
			String page = null;
			
			model.NewsDao newsDao = new model.NewsDao();
			model.BannerDao bannerDao = new model.BannerDao();
			model.CategoryDao categoryDao = new model.CategoryDao();
			model.MemberDao memberDao = new model.MemberDao();
			model.BoardDao boardDao = new model.BoardDao();
			model.FestivalDao festivalDao = new model.FestivalDao();

			if (uri == null || uri.equals("/") || uri.equals("/main.do")) {
				request.setAttribute("memberCount", memberDao.getTotalMembers());
				request.setAttribute("boardCount", boardDao.getBoardCount());
				request.setAttribute("festivalCount", festivalDao.getFestivalCount(null));
				request.setAttribute("newsCount", newsDao.getNewsCount());
				page = "/WEB-INF/views/admin/admin_main.jsp";
			} else if (uri.equals("/memberList.do")) {
				new service.MemberList().doCommand(request, response);
				page = "/WEB-INF/views/admin/admin_member_list.jsp";
			} else if (uri.equals("/memberDelete.do")) {
				String userid = request.getParameter("userid");
				int result = memberDao.deleteMember(userid);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/newsManage.do")) {
				request.setAttribute("newsList", newsDao.getNewsList());
				page = "/WEB-INF/views/admin/admin_news_manage.jsp";
			} else if (uri.equals("/newsInsert.do")) {
				String title = getPartValue(request, "title");
				String category = getPartValue(request, "category");
				String linkUrl = getPartValue(request, "linkUrl");
				String content = getPartValue(request, "content");
				String imgFile = "";
				
				System.out.println("[AdminController] newsInsert.do params - title: " + title + ", category: " + category);
				
				if (title == null || title.trim().isEmpty()) {
					System.err.println("[AdminController] Error: Title is missing in newsInsert.do");
					response.setContentType("text/plain; charset=UTF-8");
					response.getWriter().print("0");
					return;
				}
				
				try {
					Part part = request.getPart("uploadFile");
					if (part != null && part.getSize() > 0) {
						String originalName = getFileName(part);
						if (originalName != null && !originalName.isEmpty()) {
							String uuidName = UUID.randomUUID().toString() + "_" + originalName;
							String savePath = util.FileConfig.UPLOAD_PATH + File.separator + "news";
							File dir = new File(savePath);
							if (!dir.exists()) dir.mkdirs();
							
							String fullSavePath = savePath + File.separator + uuidName;
							
							try (java.io.InputStream is = part.getInputStream();
								 java.io.FileOutputStream fos = new java.io.FileOutputStream(fullSavePath)) {
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									fos.write(buffer, 0, length);
								}
							}
							
							imgFile = "news/" + uuidName;
							System.out.println("[AdminController] News image saved: " + imgFile);
						}
					}
				} catch (Exception e) {
					System.err.println("[AdminController] News image upload error: " + e.getMessage());
					e.printStackTrace();
				}

				model.NewsDto dto = new model.NewsDto();
				dto.setTitle(title);
				dto.setCategory(category);
				dto.setLinkUrl(linkUrl);
				dto.setContent(content);
				dto.setImgfile(imgFile);
				
				int result = newsDao.insertNews(dto);
				System.out.println("[AdminController] newsInsert.do result: " + result);
				
				response.setContentType("text/plain; charset=UTF-8");
				if (result > 0) {
					response.getWriter().print("1");
				} else {
					response.getWriter().print("0");
				}
				return; 
			} else if (uri.equals("/newsUpdate.do")) {
				String nnoStr = getPartValue(request, "nno");
				String title = getPartValue(request, "title");
				String category = getPartValue(request, "category");
				String linkUrl = getPartValue(request, "linkUrl");
				String content = getPartValue(request, "content");
				String existingImgFile = getPartValue(request, "imgfile");

				System.out.println("[AdminController] newsUpdate.do params - nno: " + nnoStr + ", title: " + title + ", category: " + category);

				int nno = (nnoStr != null && !nnoStr.isEmpty()) ? Integer.parseInt(nnoStr) : 0;
				
				if (nno <= 0 || title == null || title.trim().isEmpty()) {
					System.err.println("[AdminController] Error: Invalid nno or missing title. nnoStr=" + nnoStr + ", title=" + title);
					response.setContentType("text/plain; charset=UTF-8");
					response.getWriter().print("0");
					return;
				}

				// 기존 데이터를 먼저 가져와서 기존 이미지 경로 유지
				model.NewsDto existingDto = newsDao.getNewsByNno(nno);
				String imgFile = (existingDto != null) ? existingDto.getImgfile() : "";
				
				if (existingImgFile != null && !existingImgFile.isEmpty()) {
					imgFile = existingImgFile;
				}

				try {
					Part part = request.getPart("uploadFile");
					if (part != null && part.getSize() > 0) {
						String originalName = getFileName(part);
						if (originalName != null && !originalName.isEmpty()) {
							String uuidName = UUID.randomUUID().toString() + "_" + originalName;
							String savePath = util.FileConfig.UPLOAD_PATH + File.separator + "news";
							File dir = new File(savePath);
							if (!dir.exists()) dir.mkdirs();
							
							String fullSavePath = savePath + File.separator + uuidName;
							
							try (java.io.InputStream is = part.getInputStream();
								 java.io.FileOutputStream fos = new java.io.FileOutputStream(fullSavePath)) {
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									fos.write(buffer, 0, length);
								}
							}
							
							// 기존 파일 삭제
							if (existingDto != null && existingDto.getImgfile() != null && !existingDto.getImgfile().isEmpty()) {
								String oldFilePath = util.FileConfig.UPLOAD_PATH + File.separator + existingDto.getImgfile().replace("/", File.separator);
								File oldFile = new File(oldFilePath);
								if (oldFile.exists()) {
									oldFile.delete();
								}
							}
							
							imgFile = "news/" + uuidName;
						}
					}
				} catch (Exception e) {
					System.err.println("[AdminController] News image update error: " + e.getMessage());
					e.printStackTrace();
				}

				model.NewsDto dto = new model.NewsDto();
				dto.setNno(nno);
				dto.setTitle(title);
				dto.setCategory(category);
				dto.setLinkUrl(linkUrl);
				dto.setContent(content);
				dto.setImgfile(imgFile);
				
				int result = newsDao.updateNews(dto);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/newsDelete.do")) {
				int nno = Integer.parseInt(request.getParameter("nno"));
				int result = newsDao.deleteNews(nno);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/bannerManage.do")) {
				request.setAttribute("bannerList", bannerDao.getAllBanners());
				page = "/WEB-INF/views/admin/admin_banner_manage.jsp";
			} else if (uri.equals("/bannerInsert.do")) {
				System.out.println("[AdminController] Processing bannerInsert.do");
				model.BannerDto dto = new model.BannerDto();
				
				String title = request.getParameter("title");
				String subtitle = request.getParameter("subtitle");
				String linkUrl = request.getParameter("link_url");
				String orderNoStr = request.getParameter("order_no");
				String isActive = request.getParameter("is_active");
				String imgFile = request.getParameter("imgfile"); // 既存のファイル名またはクラス名
			
				System.out.println("[AdminController] bannerInsert.do params: title=" + title + ", link_url=" + linkUrl + ", order_no=" + orderNoStr);
				
				try {
					Part part = request.getPart("uploadFile");
					if (part != null && part.getSize() > 0) {
						String originalName = getFileName(part);
						if (originalName != null && !originalName.isEmpty()) {
							String uuidName = UUID.randomUUID().toString() + "_" + originalName;
							String savePath = util.FileConfig.UPLOAD_PATH + File.separator + "banner";
							File dir = new File(savePath);
							if (!dir.exists()) dir.mkdirs();
							
							String fullSavePath = savePath + File.separator + uuidName;
							System.out.println("[AdminController] Saving banner to: " + fullSavePath);
							
							try (java.io.InputStream is = part.getInputStream();
								 java.io.FileOutputStream fos = new java.io.FileOutputStream(fullSavePath)) {
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									fos.write(buffer, 0, length);
								}
							}
							imgFile = "banner/" + uuidName;
							System.out.println("  File uploaded and saved: " + imgFile);
						}
					}
				} catch (Exception e) {
					System.err.println("[AdminController] File upload error in insert: " + e.getMessage());
				}
				
				dto.setTitle(title);
				dto.setSubtitle(subtitle);
				dto.setLinkUrl(linkUrl);
				dto.setOrderNo(orderNoStr != null && !orderNoStr.isEmpty() ? Integer.parseInt(orderNoStr) : 0);
				dto.setIsActive(isActive != null ? isActive : "Y");
				dto.setImgfile(imgFile != null ? imgFile : "");
				
				int result = bannerDao.insertBanner(dto);
				System.out.println("  Insert Result: " + result);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/bannerUpdate.do")) {
				System.out.println("[AdminController] Processing bannerUpdate.do");
				String banoStr = request.getParameter("bano");
				String title = request.getParameter("title");
				String subtitle = request.getParameter("subtitle");
				String linkUrl = request.getParameter("link_url");
				String orderNoStr = request.getParameter("order_no");
				String isActive = request.getParameter("is_active");
				String imgFile = request.getParameter("imgfile"); // 既存のファイル名
				
				System.out.println("  Update params - bano: " + banoStr + ", title: " + title + ", current imgFile: " + imgFile);
				
				if (banoStr == null || banoStr.isEmpty()) {
					response.getWriter().print(0);
					return;
				}
				
				model.BannerDto dto = new model.BannerDto();
				dto.setBano(Integer.parseInt(banoStr));
				
				try {
					Part part = request.getPart("uploadFile");
					if (part != null && part.getSize() > 0) {
						String originalName = getFileName(part);
						if (originalName != null && !originalName.isEmpty()) {
							String uuidName = UUID.randomUUID().toString() + "_" + originalName;
							String savePath = util.FileConfig.UPLOAD_PATH + File.separator + "banner";
							File dir = new File(savePath);
							if (!dir.exists()) dir.mkdirs();
							
							String fullSavePath = savePath + File.separator + uuidName;
							System.out.println("[AdminController] Updating banner to: " + fullSavePath);
							
							try (java.io.InputStream is = part.getInputStream();
								 java.io.FileOutputStream fos = new java.io.FileOutputStream(fullSavePath)) {
								byte[] buffer = new byte[1024];
								int length;
								while ((length = is.read(buffer)) > 0) {
									fos.write(buffer, 0, length);
								}
							}
							imgFile = "banner/" + uuidName;
							System.out.println("  New file uploaded for update: " + imgFile);
						}
					}
				} catch (Exception e) {
					System.err.println("[AdminController] File upload error in update: " + e.getMessage());
				}
				
				dto.setTitle(title);
				dto.setSubtitle(subtitle);
				dto.setLinkUrl(linkUrl);
				dto.setOrderNo(orderNoStr != null && !orderNoStr.isEmpty() ? Integer.parseInt(orderNoStr) : 0);
				dto.setIsActive(isActive != null ? isActive : "Y");
				dto.setImgfile(imgFile != null ? imgFile : "");
				
				int result = bannerDao.updateBanner(dto);
				System.out.println("  Update Result: " + result);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/bannerDelete.do")) {
				int bano = Integer.parseInt(request.getParameter("bano"));
				int result = bannerDao.deleteBanner(bano);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/categoryManage.do")) {
				request.setAttribute("categoryList", categoryDao.getAllCategories());
				page = "/WEB-INF/views/admin/admin_category_manage.jsp";
			} else if (uri.equals("/categoryInsert.do")) {
				model.CategoryDto dto = new model.CategoryDto();
				dto.setName(request.getParameter("name"));
				dto.setType(request.getParameter("type"));
				int result = categoryDao.insertCategory(dto);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/categoryDelete.do")) {
				int cno = Integer.parseInt(request.getParameter("cno"));
				int result = categoryDao.deleteCategory(cno);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/boardManage.do")) {
				// 全投稿リストを表示 (ページングなしで全件取得)
				request.setAttribute("boardList", boardDao.getBoardList(1, 1000));
				page = "/WEB-INF/views/admin/admin_board_manage.jsp";
			} else if (uri.equals("/boardDelete.do")) {
				int bno = Integer.parseInt(request.getParameter("bno"));
				int result = boardDao.boardDelete(bno);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			} else if (uri.equals("/siteInfoManage.do")) {
				model.SiteInfoDao siteInfoDao = model.SiteInfoDao.getInstance();
				request.setAttribute("siteInfoList", siteInfoDao.getAllSiteInfo());
				page = "/WEB-INF/views/admin/admin_site_info.jsp";
			} else if (uri.equals("/getSiteInfo.do")) {
				String key = request.getParameter("key");
				model.SiteInfoDao siteInfoDao = model.SiteInfoDao.getInstance();
				model.SiteInfoDto dto = siteInfoDao.getSiteInfo(key);
				
				response.setContentType("application/json; charset=UTF-8");
				if (dto == null) {
					response.getWriter().print("{}");
				} else {
					// Simple JSON response
					String json = "{" +
						"\"infoKey\":\"" + dto.getInfoKey() + "\"," +
						"\"title\":\"" + dto.getTitle().replace("\"", "\\\"") + "\"," +
						"\"content\":\"" + dto.getContent().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "") + "\"" +
						"}";
					response.getWriter().print(json);
				}
				return;
			} else if (uri.equals("/siteInfoUpdate.do")) {
				model.SiteInfoDto dto = new model.SiteInfoDto();
				dto.setInfoKey(request.getParameter("infoKey"));
				dto.setTitle(request.getParameter("title"));
				dto.setContent(request.getParameter("content"));
				
				model.SiteInfoDao siteInfoDao = model.SiteInfoDao.getInstance();
				int result = siteInfoDao.updateSiteInfo(dto);
				response.setContentType("text/plain; charset=UTF-8");
				response.getWriter().print(result);
				return;
			}

			if (page != null) {
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			System.err.println("[AdminController] Critical Error: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		if (contentDisp == null) return null;
		for (String content : contentDisp.split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
				int lastSlash = Math.max(fileName.lastIndexOf("/"), fileName.lastIndexOf("\\"));
				if (lastSlash >= 0) return fileName.substring(lastSlash + 1);
				return fileName;
			}
		}
		return null;
	}

	private String getPartValue(HttpServletRequest request, String name) {
		try {
			// Try getParameter first
			String val = request.getParameter(name);
			if (val != null && !val.isEmpty()) return val;

			// Fallback to Part
			Part part = request.getPart(name);
			if (part == null) return null;
			
			try (java.util.Scanner s = new java.util.Scanner(part.getInputStream(), "UTF-8")) {
				return s.hasNextLine() ? s.nextLine() : "";
			}
		} catch (Exception e) {
			return null;
		}
	}
}
