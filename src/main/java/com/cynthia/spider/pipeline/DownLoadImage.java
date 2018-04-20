package com.cynthia.spider.pipeline;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.ImageDao;
import com.cynthia.spider.model.Image;
import com.cynthia.utils.Constants;

@Component("DownLoadImage")
public class DownLoadImage {

	private static boolean isSuccess;
	@Resource
	private ImageDao imageDao;
	
	private static String cookie;
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
	
	public void saveImage() {
		
		String isSaved = "0";
		List<Image> images = imageDao.findImage(isSaved,10000);
		
		for (int i = 0; i < images.size(); i++) {
			isSuccess = false;
			try {
				downloadImage(images.get(i).getShowImgPath(), "pivix" + images.get(i).getImageId() + ".jpg", Constants.IMG_PATH , images.get(i).getImageId());
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} 
			if(isSuccess) {
				isSaved = "1";
				imageDao.update(isSaved,images.get(i).getImageId());
			}
		}
	}
	
	public void downloadImage(String urlString, String filename,String savePath,int imageId) throws Exception {
		//new一个URL对象  
        URL url = new URL(urlString);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"
        conn.setRequestProperty("Cookie", cookie);
        conn.setRequestProperty("Referer", "http://www.pixiv.net/member_illust.php?mode=medium&illust_id=" + imageId);
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  bie 
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(savePath + filename);  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        isSuccess = true;
        //关闭输出流  
        outStream.close();   
	}
	private byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }
}
