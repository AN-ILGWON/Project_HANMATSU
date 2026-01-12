package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Command;
import model.TourFestivalDto;
import util.TourApiService;

public class TourFestivalMain implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		TourApiService apiService = new TourApiService();
		
		// メインページ用: 現在日付基準で 4個だけ取得
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd");
		String startDate = sdf.format(new java.util.Date());
		
		String jsonResponse = apiService.getFestivalList(startDate, null, 1, 4);
		List<TourFestivalDto> list = new ArrayList<>();
		
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
						
						for (int i = 0; i < itemArray.length(); i++) {
							JSONObject item = itemArray.getJSONObject(i);
							TourFestivalDto dto = new TourFestivalDto();
							dto.setContentId(item.optString("contentid"));
							dto.setTitle(item.optString("title"));
							dto.setAddr1(item.optString("addr1"));
							dto.setFirstImage(item.optString("firstimage"));
							dto.setEventStartDate(item.optString("eventstartdate"));
							dto.setEventEndDate(item.optString("eventenddate"));
							list.add(dto);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("tourMainList", list);
	}
}
