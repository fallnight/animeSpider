package com.cynthia.spider.pipeline;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.ImageDao;
import com.cynthia.spider.model.Image;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("pivixRecommendPipeline")
public class PivixRecommendPipeline implements PageProcessor{

	
	@Resource
	private ImageDao imageDao;
	
	private Site site = Site.me().setSleepTime(5000).setTimeOut(10000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
			.setCycleRetryTimes(5);
			
			
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	public void process(Page page) {
		if(page.getUrl().toString().startsWith("http://www.google.com")) {
			
			List<Image> list = imageDao.findImageForUpdate(10000);
			
			for (Image image : list) {
				page.addTargetRequest("http://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + image.getImageId());
			}
		}
		if(page.getUrl().toString().startsWith("http://www.pixiv.net/member_illust.php")) {
			int view = Integer.parseInt(page.getHtml().xpath("//section[@class='score']/ul/li[1]/span[2]/text()").toString());
			int rated = Integer.parseInt(page.getHtml().xpath("//section[@class='score']/ul/li[2]/span[2]/text()").toString());
			String url = page.getUrl().toString();
			int imageId = Integer.parseInt(url.substring(url.lastIndexOf("=") + 1));
			imageDao.updateImage(view, rated, imageId);
		}
	}
	
	public Site getSite() {
		return site;
	}
}
