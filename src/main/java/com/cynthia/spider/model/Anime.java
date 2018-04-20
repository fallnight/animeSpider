package com.cynthia.spider.model;

import java.util.Date;

public class Anime {
	String author;
	String title;
	String content;
	int stow;
	String type;
	int playNum;
	int danmuNum;
	String url;
	Date createTime;
	
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
	public int getPlayNum() {
		return playNum;
	}
	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}
	public int getDanmuNum() {
		return danmuNum;
	}
	public void setDanmuNum(int danmuNum) {
		this.danmuNum = danmuNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStow() {
		return stow;
	}
	public void setStow(int stow) {
		this.stow = stow;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
