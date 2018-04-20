package com.cynthia.spider.pipeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.cynthia.spider.model.Anime;

import us.codecraft.webmagic.Page;

public class BaseFunction {

	public String videoType;
	
	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}
	
	public Anime pagePipeline(Page page,int i) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Anime anime = new Anime();
			anime.setTitle(page.getHtml().xpath("//ul[@class='vd-list']/li["+ i +"]//a[@class='title']/text()").toString());
			anime.setAuthor(page.getHtml().xpath("//ul[@class='vd-list']/li["+ i +"]//a[@class='v-author']/text()").toString());
			anime.setContent(page.getHtml().xpath("//ul[@class='vd-list']/li["+ i +"]//div[@class='v-desc']/text()").toString());
			String playNum = page.getHtml().xpath("//ul[@class='vd-list']/li["+ i +"]//span[@class='gk']/span/text()").toString();
			if(!"--".equals(playNum)) {
				anime.setPlayNum(Integer.parseInt(playNum));
			}
			anime.setStow(Integer.parseInt(page.getHtml().xpath("//ul[@class='vd-list']/li["+ i +"]//span[@class='sc']/span/text()").toString()));
			anime.setDanmuNum(Integer.parseInt(page.getHtml().xpath("//ul[@class='vd-list']/li[" + i +"]//span[@class='dm']/span/text()").toString()));
			anime.setUrl(page.getHtml().xpath("//ul[@class='vd-list']/li[" + i +"]//a[@class='title']/@href").toString());
			String date = page.getHtml().xpath("//ul[@class='vd-list']/li[" + i +"]//span[@class='v-date']/text()").toString();
			if(!"--".equals(date)) {
				try {
					anime.setCreateTime(sdf.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return anime;
	}
	
}
