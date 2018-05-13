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
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class EhentaiCrawler {

	Request request = new Request();

	@Resource
	private ProxyIpDao proxyIpDao;
	
	@Qualifier("EHentaiPipeline")
	@Autowired
	private PageProcessor eHentaiPipeline;
	
	public void ehentaiCrawl() {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1",1080)));
		request.setUrl("https://e-hentai.org/");
		Spider.create(eHentaiPipeline)
						.addRequest(request).setDownloader(httpClientDownloader)
						.thread(3).run();
    }
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
        final EhentaiCrawler ehentaiCrawler = applicationContext.getBean(EhentaiCrawler.class);
        
        ehentaiCrawler.ehentaiCrawl();

	}
}
