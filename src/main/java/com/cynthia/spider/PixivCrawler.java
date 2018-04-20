package com.cynthia.spider;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.ProxyIpDao;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class PixivCrawler {

	Request request = new Request();

	@Resource
	private ProxyIpDao proxyIpDao;
	
	@Qualifier("PixivPipeline")
	@Autowired
	private PageProcessor pixivPipeline;
	
	//短片
	public void pixivCrawl() {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		request.setUrl("https://www.pixiv.net/rpc/recommender.php?type=illust&sample_illusts=auto&num_recommendations=1000&page=discovery&mode=all");
		Spider.create(pixivPipeline)
						.addRequest(request).setDownloader(httpClientDownloader)
						.thread(3).run();
    }
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
        final PixivCrawler movieCrawler = applicationContext.getBean(PixivCrawler.class);
        
        movieCrawler.pixivCrawl();

	}
}
