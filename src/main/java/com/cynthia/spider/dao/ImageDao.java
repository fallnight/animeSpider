package com.cynthia.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cynthia.spider.model.Image;

public interface ImageDao {
	@Insert("insert ignore into pixiv (`title`,`view`,`rated`,`score`,`user`,`caption`,`imagePath`,`createDate`,`imageId`,`realImgPath`,`size`,`showImgPath`,`tags`) values (#{title},#{view},#{rated},#{score},#{user},#{caption},#{imagePath},#{createDate},#{imageId},#{realImgPath},#{size},#{showImgPath},#{tags})")
	public void add(Image image);
	
	@Update("update pixiv set isSaved = #{0} where imageId = #{1}")
	public void update(String isSaved,int imageId);
	
	@Select("select * from pixiv where isSaved = #{0} limit 0,#{1}")
	public List<Image> findImage(String isSaved,int count);
	
	@Select("select * from pixiv where isUpdate = 0 limit 0,#{0}")
	public List<Image> findImageForUpdate(int count);
	
	@Update("update pixiv set view = #{0},rated = #{1},isUpdate = 1 where imageId = #{2}")
	public void updateImage(int view, int rated, int imageId);
}
