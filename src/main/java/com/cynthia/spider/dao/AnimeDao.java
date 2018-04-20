package com.cynthia.spider.dao;

import org.apache.ibatis.annotations.Insert;

import com.cynthia.spider.model.Anime;

public interface AnimeDao {
	@Insert("insert into anime (`author`,`title`,`content`,`stow`,`playNum`,`danmuNum`,`url`,`createTime`,`type`) values (#{author},#{title},#{content},#{stow},#{playNum},#{danmuNum},#{url},#{createTime},#{type})")
	public void add(Anime news);
}
