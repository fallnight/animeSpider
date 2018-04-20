package com.cynthia.spider.pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("ImagePipeline")
public class ImagePipeline implements PageProcessor{

	private Site site = Site.me().setSleepTime(1000).setTimeOut(60000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0")
			.setCycleRetryTimes(5).addHeader("Cookie", "PHPSESSID=11639127_ae3f58671bb661e01afbe8b51de53543; p_ab_id=5; device_token=d7be8a56ef827f9bcaaf673ea47036fc" +
"; module_orders_mypage=%5B%7B%22name%22%3A%22recommended_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name" +
"%22%3A%22everyone_new_illusts%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22following_new_illusts" +
"%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22mypixiv_new_illusts%22%2C%22visible%22%3Atrue%7D%2C" +
"%7B%22name%22%3A%22fanbox%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22featured_tags%22%2C%22visible"+
"%22%3Atrue%7D%2C%7B%22name%22%3A%22contests%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22sensei_courses" +
"%22%2C%22visible%22%3Atrue%7D%2C%7B%22name%22%3A%22spotlight%22%2C%22visible%22%3Atrue%7D%2C%7B%22name" +
"%22%3A%22booth_follow_items%22%2C%22visible%22%3Atrue%7D%5D").addHeader("Referer", "http://www.pixiv.net/search.php?word=%E9%9F%BF(%E8%89%A6%E9%9A%8A%E3%81%93%E3%82%8C%E3%81%8F%E3%81%97%E3%82%87%E3%82%93)&s_mode=s_tag_full&order=date_d&p=3");
	
	public void process(Page page) {
		// TODO Auto-generated method stub
		byte[] data = page.getRawText().getBytes();
		//new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File("E:/Pixiv/1.jpg");  
        //创建输出流  
        FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(imageFile);
			 //写入数据  
	        outStream.write(data);  
	        //关闭输出流  
	        outStream.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	
}
