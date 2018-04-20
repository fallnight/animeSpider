package com.cynthia.spider.pipeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.VideoDao;
import com.cynthia.spider.model.Video;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("VideoPipelineVer2")
public class VideoPipelineVer2 implements PageProcessor{
	
	
	@Resource
	private VideoDao videoDao;
	
	private Site site = Site.me().setSleepTime(100).setTimeOut(3000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
			.setCycleRetryTimes(5).addCookie("532000181@qq.com", "wx931018");
	
	public BaseFunction bf = new BaseFunction();
	
	public void process(Page page) {
		
		if(page.getUrl().regex("http://www.bilibili.com/video/av.*").match()) {
			int avNum = Integer.parseInt(page.getUrl().toString().substring(32));
			if(avNum < 5400000) {
				List<String> urls = new ArrayList<String>();
				if(avNum == 2404699) {
					for (int i = 1; i <= 1; i++) {
						urls.add("http://www.bilibili.com/video/av" + (avNum + i));
					}
					page.addTargetRequests(urls);
				}
				if(avNum % 50 == 0 || avNum == 1) {
					for (int i = 1; i <= 50; i++) {
						urls.add("http://www.bilibili.com/video/av" + (avNum + i));
					}
					page.addTargetRequests(urls);
				}
				String title = page.getHtml().xpath("//div[@class='v-title']/h1/text()").toString();
				String content = page.getHtml().xpath("//div[@id='v_desc']/text()").toString();
				if(content == null) {
					content = "";
				}
				if(!"".equals(title) && title != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Video video = new Video();
					video.setTitle(title);
					video.setAuthor(page.getHtml().xpath("//a[@class='name']/@card").toString());
					video.setContent(content);
					String datetime = page.getHtml().xpath("//time[@itemprop='startDate']/i/text()").toString();
					try {
						if(!"".equals(datetime) && datetime != null) {
							video.setCreatetime(sdf.parse(datetime));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					video.setUrl(page.getUrl().toString());
					video.setType(page.getHtml().xpath("//span[@typeof='v:Breadcrumb']/a/text()").toString());
					
					videoDao.add(video);
				}
			}
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	
	
}
