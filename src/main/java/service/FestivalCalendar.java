package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.FestivalDao;
import model.FestivalDto;

public class FestivalCalendar implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		FestivalDao dao = new FestivalDao();
		List<FestivalDto> festivalList = dao.getAllFestivals();
		
		request.setAttribute("festivalList", festivalList);
	}
}
