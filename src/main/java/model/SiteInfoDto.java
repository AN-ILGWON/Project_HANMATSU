package model;

import java.sql.Timestamp;

public class SiteInfoDto {
    private String infoKey;
    private String title;
    private String content;
    private Timestamp updatedDate;

    public String getInfoKey() { return infoKey; }
    public void setInfoKey(String infoKey) { this.infoKey = infoKey; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(Timestamp updatedDate) { this.updatedDate = updatedDate; }
}
