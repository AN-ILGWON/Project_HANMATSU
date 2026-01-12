package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Command;
import model.TourFestivalDto;
import model.VisitedDao;
import util.TourApiService;

public class TourFestivalView implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contentId = request.getParameter("contentId");
		if(contentId == null || contentId.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/festival/apiList.do");
			return;
		}
		
		TourApiService apiService = new TourApiService();
		String jsonResponse = apiService.getDetailCommon(contentId);
		
		TourFestivalDto dto = new TourFestivalDto();
		
		if (jsonResponse != null) {
			try {
				JSONObject obj = new JSONObject(jsonResponse);
				JSONObject responseObj = obj.getJSONObject("response");
				JSONObject bodyObj = responseObj.getJSONObject("body");
				
				if (bodyObj.getInt("totalCount") > 0) {
					Object itemsObj = bodyObj.get("items");
					if (itemsObj instanceof JSONObject) {
						JSONObject items = (JSONObject) itemsObj;
						JSONArray itemArray = items.getJSONArray("item");
						JSONObject item = itemArray.getJSONObject(0);
						
						dto.setContentId(item.optString("contentid"));
						dto.setTitle(item.optString("title"));
						dto.setAddr1(item.optString("addr1"));
						dto.setFirstImage(item.optString("firstimage"));
						dto.setMapX(item.optString("mapx"));
						dto.setMapY(item.optString("mapy"));
						dto.setTel(item.optString("tel"));
						dto.setOverview(item.optString("overview"));
						
						// 訪問記録を保存
						HttpSession session = request.getSession();
						String userid = (String) session.getAttribute("userid");
						if (userid != null) {
							new VisitedDao().insertVisited(userid, contentId, dto.getTitle());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("festival", dto);
	}
}
