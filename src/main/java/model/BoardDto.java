package model;

public class BoardDto {
	
	private int bno; // 投稿番号
	private String userid; // 投稿者ID
	private String username; // 投稿者名
	private String title; // タイトル
	private String content; // 内容
	private int views; // 閲覧数
	private int likes; // いいね数
	private String regdate; // 登録日
	private String imgfile; // 添付画像ファイル名
	private int replyCount; // コメント数
	private String category; // カテゴリ (祭りレビュー, 自由掲示板, Q&A)
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getImgfile() {
		return imgfile;
	}
	public void setImgfile(String imgfile) {
		this.imgfile = imgfile;
	}
}

