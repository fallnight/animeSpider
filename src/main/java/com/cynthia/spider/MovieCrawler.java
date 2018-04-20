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
public class MovieCrawler {

	Request request = new Request();
	/*@Qualifier("AnimePipeline")
	@Autowired
	private PageProcessor animePipeline;*/
	
	@Qualifier("MoviePipeline")
	@Autowired
	private PageProcessor moviePipeline;
	
	//短片
	public void micromovieCrawl() {
		request.setUrl("http://api.bilibili.com/archive_rank/getarchiverankbypartion?callback=jQuery172006524573556990121_1482129542780&type=jsonp&tid=145&pn=1&type=jsonp&_=1468488357906");
		Spider.create(moviePipeline)
						.addRequest(request)
						.thread(5).run();
    }
	
	//短片
		public void updateSourceCrawl() {
			request.setUrl("http://www.baidu.com");
			Spider.create(moviePipeline)
							.addRequest(request)
							.thread(15).run();
	    }
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final MovieCrawler movieCrawler = applicationContext.getBean(MovieCrawler.class);

//        movieCrawler.micromovieCrawl();
        movieCrawler.updateSourceCrawl();
//        movieCrawler.westMovieCrawl();
//        movieCrawler.movieJapanCrawl();
//        movieCrawler.movieChineseCrawl();
//        movieCrawler.moviemovieCrawl();

	}
}
