package model;

public class TourFestivalDto {
    private String contentId;
    private String title;
    private String addr1;
    private String firstImage;
    private String eventStartDate;
    private String eventEndDate;
    private String mapX;
    private String mapY;
    private String tel;
    private String overview;

    public String getContentId() { return contentId; }
    public void setContentId(String contentId) { this.contentId = contentId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAddr1() { return addr1; }
    public void setAddr1(String addr1) { this.addr1 = addr1; }

    public String getFirstImage() { return firstImage; }
    public void setFirstImage(String firstImage) { this.firstImage = firstImage; }

    public String getEventStartDate() { return eventStartDate; }
    public void setEventStartDate(String eventStartDate) { this.eventStartDate = eventStartDate; }

    public String getEventEndDate() { return eventEndDate; }
    public void setEventEndDate(String eventEndDate) { this.eventEndDate = eventEndDate; }

    public String getMapX() { return mapX; }
    public void setMapX(String mapX) { this.mapX = mapX; }

    public String getMapY() { return mapY; }
    public void setMapY(String mapY) { this.mapY = mapY; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }
}
