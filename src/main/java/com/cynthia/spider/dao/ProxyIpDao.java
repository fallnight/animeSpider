package com.cynthia.spider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.cynthia.spider.model.ProxyIp;

public interface ProxyIpDao {

	@Select("select ip,port from proxys")
	public List<ProxyIp> findAllIp();
}
