package com.cynthia.spider.pipeline;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.AnimeDao;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("VideoPipeline")
public class VideoPipeline implements PageProcessor{

	public BaseFunction bf = new BaseFunction();
	
	@Resource
	private AnimeDao animeDao;
	String type;
	
	private Site site = Site.me().setSleepTime(100).setTimeOut(300)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
			.setCycleRetryTimes(3);
	
	
	
	public void process(Page page) {
		if(page.getRequest().getExtra("type") != null && !"".equals(page.getRequest().getExtra("type").toString())) {
			type = page.getRequest().getExtra("type").toString();
		}
		List<String> links = page.getHtml().links().regex("http://www.bilibili.com/video/"+type+".*").all();
		page.addTargetRequests(links);
		
		
		
//		for (int i = 1; i <= 20; i++) {
//			Anime anime = new Anime();
//			anime = bf.pagePipeline(page,i);
//			anime.setType(type);
//			animeDao.add(anime);
//		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
}
