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
public class PixivRecommCrawler {

	Request request = new Request();

	@Resource
	private ProxyIpDao proxyIpDao;
	
	@Qualifier("pivixRecommendPipeline")
	@Autowired
	private PageProcessor pivixRecommendPipeline;
	
	//短片
	public void pixivCrawl() {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		request.setUrl("http://www.google.com");
		Spider.create(pivixRecommendPipeline)
						.addRequest(request).setDownloader(httpClientDownloader)
						.thread(3).run();
    }
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
        final PixivRecommCrawler movieCrawler = applicationContext.getBean(PixivRecommCrawler.class);
        
        movieCrawler.pixivCrawl();

	}
}
