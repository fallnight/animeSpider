package com.cynthia.spider.model;

import java.util.Date;

public class Image {

	String title;
	int view;
	int rated;
	int score;
	String user;
	String caption;
	String imagePath;
	Date createDate;
	int imageId;
	String size;
	String realImgPath;
	String showImgPath;
	String tags;
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getTitle() {
		return title;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getRated() {
		return rated;
	}
	public void setRated(int rated) {
		this.rated = rated;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRealImgPath() {
		return realImgPath;
	}
	public void setRealImgPath(String realImgPath) {
		this.realImgPath = realImgPath;
	}
	public String getShowImgPath() {
		return showImgPath;
	}
	public void setShowImgPath(String showImgPath) {
		this.showImgPath = showImgPath;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [title=");
		builder.append(title);
		builder.append(", view=");
		builder.append(view);
		builder.append(", rated=");
		builder.append(rated);
		builder.append(", score=");
		builder.append(score);
		builder.append(", user=");
		builder.append(user);
		builder.append(", caption=");
		builder.append(caption);
		builder.append(", imagePath=");
		builder.append(imagePath);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", imageId=");
		builder.append(imageId);
		builder.append(", size=");
		builder.append(size);
		builder.append(", realImgPath=");
		builder.append(realImgPath);
		builder.append(", showImgPath=");
		builder.append(showImgPath);
		builder.append(", tags=");
		builder.append(tags);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
