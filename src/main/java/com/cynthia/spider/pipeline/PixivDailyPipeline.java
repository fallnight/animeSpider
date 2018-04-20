package com.cynthia.spider.pipeline;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cynthia.spider.dao.ImageDao;
import com.cynthia.spider.model.Image;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("pixivDailyPipeline")
public class PixivDailyPipeline implements PageProcessor{

	@Resource
	private ImageDao imageDao;
	
	private static int totleNum = 0;
	
	private Site site = Site.me().setSleepTime(5000).setTimeOut(10000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
			.setCycleRetryTimes(5);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	public void process(Page page) {
		
		if(page.getUrl().toString().startsWith("https://www.pixiv.net/ranking.php")) {
			String jsonData = null;
			try {
				jsonData = page.getRawText().replaceAll("%(?![0-9a-fA-F]{2})", "%25");  
				jsonData = jsonData.replaceAll("\\+", "%2B");  
				jsonData = URLDecoder.decode(jsonData, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			JSONObject json = JSONObject.parseObject(jsonData);
			JSONArray contents = json.getJSONArray("contents");
			
			totleNum += contents.size();
			for (int i = 0; i < contents.size(); i++) {
				JSONObject content = contents.getJSONObject(i);
				Image image = new Image();
				image.setView(Integer.parseInt(content.getString("view_count")));
				image.setRated(Integer.parseInt(content.getString("rating_count")));
				image.setTitle(content.getString("title"));
				image.setUser(content.getString("user_id"));
				String url = content.getString("url");
				image.setShowImgPath(url);
				String subStr = url.substring(url.indexOf("/c/") + 3, url.indexOf("/c/") + 10);
				image.setImagePath(content.getString("url").replaceFirst(subStr, "600x600"));
				String date = content.getString("date");
				if(!"".equals(date) && date != null) {
					try {
						image.setCreateDate(sdf.parse(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				image.setSize(content.getString("width") + "x" + content.getString("height"));
				image.setImageId(Integer.parseInt(content.getString("illust_id")));
				image.setTags(content.getString("tags"));
				imageDao.add(image);
			}
			String next = json.getString("next");
			String date = json.getString("date");
			String nextDate = json.getString("next_date");
			if(!"false".equals(nextDate)) {
				if(!"false".equals(next) && totleNum <= 200) {
					page.addTargetRequest("https://www.pixiv.net/ranking.php?mode=daily&date=" + date + "&p=" + next + "&format=json&tt=bfd98efa57d665d56de7e13892b76fe8");
				} else {
					totleNum = 0;
					page.addTargetRequest("https://www.pixiv.net/ranking.php?mode=daily&date=" + nextDate + "&p=1&format=json&tt=bfd98efa57d665d56de7e13892b76fe8");
				}
			}
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	
}
