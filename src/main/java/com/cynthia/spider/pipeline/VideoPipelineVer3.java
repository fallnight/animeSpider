package com.cynthia.spider.pipeline;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.VideoDao;
import com.cynthia.spider.model.Video;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

@Component("VideoPipelineVer3")
public class VideoPipelineVer3 implements PageProcessor{

	@Resource
	private VideoDao videoDao;
	
	private Site site = Site.me().setSleepTime(100).setTimeOut(3000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
			.setCycleRetryTimes(5);
	
	
	public void process(Page page) {
		
		if(page.getUrl().regex(".*aid=.*").match()) {
			int avNum = Integer.parseInt(page.getUrl().toString().substring(98,page.getUrl().toString().indexOf("&type=jsonp")));
			if(avNum < 5400000) {
				List<String> urls = new ArrayList<String>();
				if(avNum % 50 == 0 || avNum == 1) {
					for (int i = 1; i <= 50; i++) {
						urls.add("http://api.bilibili.com/archive_stat/stat?callback=jQuery1720015561035711100124_1468488356799&aid=" + (avNum + i) + "&type=jsonp&_=1468488357906");
					}
					page.addTargetRequests(urls);
				}
				String jsonData = page.getRawText().substring(43,page.getRawText().indexOf(")"));
				Video video = new Video();
				video.setView(Integer.parseInt(new JsonPathSelector("$.data.view").select(jsonData).toString()));
				video.setDanmaku(Integer.parseInt(new JsonPathSelector("$.data.danmaku").select(jsonData).toString()));
				video.setReply(Integer.parseInt(new JsonPathSelector("$.data.reply").select(jsonData).toString()));
				video.setFavorite(Integer.parseInt(new JsonPathSelector("$.data.favorite").select(jsonData).toString()));
				video.setCoin(Integer.parseInt(new JsonPathSelector("$.data.coin").select(jsonData).toString()));
				video.setShare(Integer.parseInt(new JsonPathSelector("$.data.share").select(jsonData).toString()));
				video.setUrl("http://www.bilibili.com/video/av" + avNum);
				
				videoDao.update(video);
			}
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	
}
