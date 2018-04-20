package com.cynthia.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cynthia.spider.model.Movie;

public interface MovieDao {

	@Insert("insert into movie (`author`,`title`,`content`,`favorite`,`view`,`danmaku`,`reply`,`share`,`coin`,`url`,`createtime`) values (#{author},#{title},#{content},#{favorite},#{view},#{danmaku},#{reply},#{share},#{coin},#{url},#{createtime})")
	public void add(Movie movie);
	
	@Update("update movie set sourceurl = #{sourceUrl} where url = #{url}")
	public void update(Movie movie);
	
	@Select("select url from movie where sourceurl is null")
	public List<Movie> findSourceUrl();
}
