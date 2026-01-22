package model;

public class BannerDto {
    private int bano;
    private String title;
    private String subtitle;
    private String imgfile;
    private String linkUrl;
    private int orderNo;
    private String isActive;

    public int getBano() { return bano; }
    public void setBano(int bano) { this.bano = bano; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }
    public String getImgfile() { return imgfile; }
    public void setImgfile(String imgfile) { this.imgfile = imgfile; }
    public String getLinkUrl() { return linkUrl; }
    public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
    public int getOrderNo() { return orderNo; }
    public void setOrderNo(int orderNo) { this.orderNo = orderNo; }
    public String getIsActive() { return isActive; }
    public void setIsActive(String isActive) { this.isActive = isActive; }
}
