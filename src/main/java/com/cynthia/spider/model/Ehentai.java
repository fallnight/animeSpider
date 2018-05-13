package com.cynthia.spider.model;

import java.util.Date;

public class Ehentai {

	private int id;
	
	private String title;
	
	private String fileSize;
	
	private int length;
	
	private int favorited;
	
	private String ratingAverage;
	
	private int ratingNum;
	
	private int imageId;
	
	private String coverUrl;
	
	private String imageUrl;

	private String alias;
	
	private Date published;
	
	private String types;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFavorited() {
		return favorited;
	}

	public void setFavorited(int favorited) {
		this.favorited = favorited;
	}

	public String getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(String ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public int getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(int ratingNum) {
		this.ratingNum = ratingNum;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

}
