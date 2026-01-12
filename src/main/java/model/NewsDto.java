package model;

public class NewsDto {
    private int nno;
    private String title;
    private String category;
    private String regdate;
    private String imgfile;
    private String linkUrl;

    public int getNno() { return nno; }
    public void setNno(int nno) { this.nno = nno; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getRegdate() { return regdate; }
    public void setRegdate(String regdate) { this.regdate = regdate; }
    public String getImgfile() { return imgfile; }
    public void setImgfile(String imgfile) { this.imgfile = imgfile; }
    public String getLinkUrl() { return linkUrl; }
    public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
}
