package com.cynthia.spider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
public class PixivDailyCrawler {

	Request request = new Request();

	@Resource
	private ProxyIpDao proxyIpDao;
	
	@Qualifier("pixivDailyPipeline")
	@Autowired
	private PageProcessor pixivPipeline;
	
	//短片
	public void pixivCrawl() {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		request.setUrl("https://www.pixiv.net/ranking.php?mode=daily&date="+ getDate(new Date(), 7) +"&p=1&format=json&tt=bfd98efa57d665d56de7e13892b76fe8");
		Spider.create(pixivPipeline)
						.addRequest(request).setDownloader(httpClientDownloader)
						.thread(1).run();
    }
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
        final PixivDailyCrawler movieCrawler = applicationContext.getBean(PixivDailyCrawler.class);
        
        movieCrawler.pixivCrawl();
	}
	
	private static String getDate(Date d,int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DATE,calendar.get(Calendar.DATE) - day);
		return sdf.format(calendar.getTime());	
	}
}
