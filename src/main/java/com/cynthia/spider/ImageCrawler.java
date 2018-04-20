package com.cynthia.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class ImageCrawler {

	Request request = new Request();
	/*@Qualifier("AnimePipeline")
	@Autowired
	private PageProcessor animePipeline;*/
	
	@Qualifier("ImagePipeline")
	@Autowired
	private PageProcessor imagePipeline;
	
	//短片
	public void imageCrawl() {
		request.setUrl("http://i1.pixiv.net/c/600x600/img-master/img/2016/12/20/00/06/45/60453180_p0_master1200.jpg");
		Spider.create(imagePipeline)
						.addRequest(request)
						.thread(5).run();
    }
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final ImageCrawler imageCrawler = applicationContext.getBean(ImageCrawler.class);

        imageCrawler.imageCrawl();
	}
	
}
