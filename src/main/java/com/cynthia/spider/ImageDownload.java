package com.cynthia.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.cynthia.spider.pipeline.DownLoadImage;

@Component
public class ImageDownload {
	
	private static Logger logger = LoggerFactory.getLogger(ImageDownload.class);
	
	@Qualifier("DownLoadImage")
	@Autowired
	private DownLoadImage downLoadImage;
	
		public void saveImage() {
			downLoadImage.saveImage();
	    }
		
		
		public static void main(String[] args) {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
	        final ImageDownload imageDownload = applicationContext.getBean(ImageDownload.class);
	        imageDownload.saveImage();
	        logger.info("图片下载完成！");
		}
}
