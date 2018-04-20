package com.cynthia.spider.model;

import java.util.Date;

public class Video {

	String author;
	String title;
	String content;
	int favorite;
	String type;
	int view;
	int danmaku;
	int reply;
	int share;
	int coin;
	String url;
	Date createtime;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public int getFavorite() {
		return favorite;
	}
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getDanmaku() {
		return danmaku;
	}
	public void setDanmaku(int danmaku) {
		this.danmaku = danmaku;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;  
	}
	public Date getCreatetime() {
		return createtime; 
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}


