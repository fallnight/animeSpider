package com.cynthia.spider.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.cynthia.spider.model.Ehentai;

public interface EhentaiDao {
	@Insert("insert ignore into ehentai (`title`,`imageId`,`coverUrl`,`imageUrl`) values (#{title},#{imageId},#{coverUrl},#{imageUrl})")
	public void add(Ehentai ehentai);
	
	@Update("update ehentai set published = #{published},fileSize = #{fileSize},types = #{types},length = #{length},favorited = #{favorited},ratingAverage = #{ratingAverage},ratingNum = #{ratingNum},alias = #{alias} where imageId = #{imageId}")
	public void update(Ehentai ehentai);
	
}
