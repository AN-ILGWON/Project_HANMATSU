package test;

import model.FestivalDao;
import model.FestivalDto;
import java.sql.Connection;
import util.DBManager;

public class TestInsert {
    public static void main(String[] args) {
        FestivalDao dao = new FestivalDao();
        FestivalDto dto = new FestivalDto();
        dto.setRegion("테스트지역");
        dto.setName("테스트축제_" + System.currentTimeMillis());
        dto.setDescription("테스트 설명입니다.");
        dto.setStartDate("2026-05-01");
        dto.setEndDate("2026-05-05");
        dto.setLocation("테스트 장소");
        dto.setImgfile("test.jpg");
        dto.setHomepage("http://test.com");
        dto.setInstagram("@test");
        dto.setMapUrl("http://map.com");
        dto.setLikes(0);
        dto.setIsRecommended("N");

        System.out.println("Attempting to insert festival...");
        int result = dao.festivalInsert(dto);
        
        if (result > 0) {
            System.out.println("SUCCESS: Festival inserted successfully!");
        } else {
            System.out.println("FAILURE: Festival insert failed.");
        }
    }
}
