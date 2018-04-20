package com.cynthia.spider.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.cynthia.spider.model.Video;

public interface VideoDao {

	@Insert("insert into bilibili (`author`,`title`,`content`,`url`,`createtime`,`type`) values (#{author},#{title},#{content},#{url},#{createtime},#{type})")
	public void add(Video video);
	
	@Update("update bilibili set favorite = #{favorite},view = #{view},danmaku = #{danmaku},reply = #{reply},share = #{share},coin = #{coin} where url = #{url}")
	public void update(Video video);
}
