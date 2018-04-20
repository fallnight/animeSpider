package com.cynthia.spider.pipeline;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.ImageDao;
import com.cynthia.spider.model.Image;
import com.cynthia.utils.Constants;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("PixivPipeline")
public class PivixPipeline implements PageProcessor{

	private static String cookie;

	//https://accounts.pixiv.net/login?lang=zh&source=pc&view_type=page&ref=wwwtop_accounts_index
	static {
		File file = new File(Constants.COOKIE_PATH + "/PivixCookie.dat");
		BufferedInputStream input = null;
		byte be[] = new byte[1024];
		try {
			
			input = new BufferedInputStream(new FileInputStream(file));
			input.read(be);
			
			cookie = new String(be,0,be.length).trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Resource
	private ImageDao imageDao;
	
	private Site site = Site.me().setSleepTime(5000).setTimeOut(10000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
			.setCycleRetryTimes(5)
			.addHeader("Cookie", cookie)
			.addHeader("Referer", "https://www.pixiv.net/discovery");
			
			
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	public void process(Page page) {
		if(page.getUrl().toString().startsWith("https://www.pixiv.net/rpc/recommender.php")) {
			String jsonData = page.getRawText();
			String[] ids = jsonData.substring(jsonData.indexOf("[") + 1, jsonData.lastIndexOf("]")).split(",");
			
			for (int i = 0; i < ids.length; i++) {
				page.addTargetRequest("http://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + ids[i]);
			}
		}
		if(page.getUrl().toString().startsWith("http://www.pixiv.net/member_illust.php")) {
			Image image = new Image();
			int view = Integer.parseInt(page.getHtml().xpath("//dd[@class='view-count']/text()").toString());
			int rate = Integer.parseInt(page.getHtml().xpath("//dd[@class='rated-count']/text()").toString());
			String size = page.getHtml().xpath("//section[@class='work-info']/ul/li[2]/text()").toString();
			String imgPath = page.getHtml().xpath("//div[@class='_layout-thumbnail']/img/@src").toString();
			image.setView(view);
			image.setRated(rate);
			image.setScore(view + rate * 10);
			if((image.getView() > 10000 || image.getScore() > 35000) && size.indexOf("×") > 0 && StringUtils.isNotBlank(imgPath)) {
				image.setTitle(page.getHtml().xpath("//section[@class='work-info']/h1/text()").toString());
				image.setUser(page.getHtml().xpath("//div[@class='profile']/a/@title").toString());
				image.setCaption(page.getHtml().xpath("//div[@class='ui-expander-target']/p/text()").toString());
				image.setImagePath(imgPath);
				String date = page.getHtml().xpath("//section[@class='work-info']/ul/li[1]/text()").toString();
				if(!"".equals(date) && date != null) {
					try {
						image.setCreateDate(sdf.parse(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				image.setSize(calculateSize(size));
				image.setRealImgPath(page.getHtml().xpath("//div[@class='_illust_modal']/div/img/@data-src").toString());
				String url = page.getUrl().toString();
				image.setImageId(Integer.parseInt(url.substring(url.lastIndexOf("=") + 1)));
				
				imageDao.add(image);
			}
		}
	}

	private String calculateSize(String size) {
		String sizes[] = size.split("×");
		double sizeWidth = Integer.parseInt(sizes[0]);
		double sizeHeigh = Integer.parseInt(sizes[1]);
		int sizeWidthInt = (int)sizeWidth;
		int sizeHeighInt = (int)sizeHeigh;
		if(sizeWidth > sizeHeighInt && sizeWidth > 600) {
			sizeWidthInt = 600;
			sizeHeighInt = (int) (Math.ceil((sizeHeigh * (600/sizeWidth))));
		} else if(sizeHeigh > sizeWidth && sizeHeigh > 600) {
			sizeWidthInt = (int) (Math.ceil((sizeWidth * (600/sizeHeigh))));
			sizeHeighInt = 600;
		}
		return sizeWidthInt + "," + sizeHeighInt;
	}
	
	public Site getSite() {
		return site;
	}
}
