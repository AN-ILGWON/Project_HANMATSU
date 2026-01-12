package model;

public class FestivalDto {
	
	private int fno; // お祭り番号
	private String region; // 地域
	private String name; // お祭り名 (日本語)
	private String description; // お祭り説明 (日本語)
	private String startDate; // 開始日
	private String endDate; // 終了日
	private String location; // 場所
	private String imgfile; // 代表画像
	private int views; // 閲覧数
	private String regdate; // 登録日
	private String homepage; // ホームページURL
	private String mapUrl; // 地図 URL
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImgfile() {
		return imgfile;
	}
	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
}

