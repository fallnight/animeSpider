package com.cynthia.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class VideoCrawler {

	@Qualifier("VideoPipelineVer2")
	@Autowired
	private PageProcessor videoPipeline;
	
	@Qualifier("VideoPipelineVer3")
	@Autowired
	private PageProcessor videoPipeline2;
	
	public void animeCrawl() {
		Spider.create(videoPipeline)
		.addUrl("http://www.bilibili.com/video/av2404699")
		.thread(15).run();
	}
	
	public void videoCrawl() {
		Spider.create(videoPipeline2)
		.addUrl("http://api.bilibili.com/archive_stat/stat?callback=jQuery1720015561035711100124_1468488356799&aid=1&type=jsonp&_=1468488357906")
		.thread(15).run();
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final VideoCrawler videoCrawler = applicationContext.getBean(VideoCrawler.class);
        videoCrawler.animeCrawl();
        //videoCrawler.videoCrawl();
	}
}
