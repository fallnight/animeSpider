package com.cynthia.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.xsoup.Xsoup;

public class LoginUtils {

	// 登陆 Url
    private static String LOGIN_API = "https://accounts.pixiv.net/api/login?lang=zh";
	
    private final static String LOGIN_URL = "https://accounts.pixiv.net/login?lang=zh&source=pc&view_type=page&ref=wwwtop_accounts_index";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUtils.class);
	
	public static Map<String, String> getParams() {
		LOGGER.info("开始获取登录页面信息成功");
		HashMap<String, String> map = new HashMap<String,String>();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(LOGIN_URL);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			LOGGER.info("获取登录页面信息成功");
			Header[] cookies = response.getHeaders("Set-Cookie");
			StringBuffer sb = new StringBuffer();
			for (int i = 1; i < cookies.length; i++) {
				String name = cookies[i].getElements()[0].getName();
				String value = cookies[i].getElements()[0].getValue();
				sb.append(name + "=" + value +"; ");
			}
			map.put("firstCookie", sb.toString() + "login_bc=1; ");
			sb.append(cookies[0].getElements()[0].getName() + "=" + cookies[0].getElements()[0].getValue()
					+ "; " + "login_bc=1");
			map.put("cookie", sb.toString());
			HttpEntity entity = response.getEntity();
			
			String responseContent = EntityUtils.toString(entity, "UTF-8");
			
			map.put("post_key", Xsoup.select(responseContent, "//input[@name='post_key']/@value").get());
			map.put("userName", "532000181@qq.com");
			map.put("password", "wx931018");
			LOGGER.info("获得登录信息",map.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
		return map;
	}
	
	public static String getPixivCookie() {
		Map<String, String> map = getParams();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		StringBuffer sb = new StringBuffer(map.get("firstCookie"));
		
		HttpPost httpPost = new HttpPost(LOGIN_API);
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");  
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");  			
		httpPost.setHeader("Origin", "https://accounts.pixiv.net");
		httpPost.setHeader("Referer", "https://accounts.pixiv.net/login?lang=zh&source=pc&view_type=page&ref=wwwtop_accounts_index");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setHeader("Cookie", map.get("cookie"));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("pixiv_id", map.get("userName")));
        params.add(new BasicNameValuePair("password", map.get("password")));
        params.add(new BasicNameValuePair("captcha", ""));
        params.add(new BasicNameValuePair("g_recaptcha_response", ""));
        params.add(new BasicNameValuePair("post_key", map.get("post_key")));
        params.add(new BasicNameValuePair("source", "pc"));
        params.add(new BasicNameValuePair("ref", "wwwtop_accounts_index"));
        params.add(new BasicNameValuePair("return_to", "http://www.pixiv.net/"));
        
        try {
        	httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = httpClient.execute(httpPost);
			LOGGER.info("登录成功");
			Header[] cookies = response.getHeaders("Set-Cookie");
			
			
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getElements()[0].getName();
				String value = cookies[i].getElements()[0].getValue();
				sb.append(name + "=" + value);
				if(i < cookies.length - 1) {
					sb.append("; ");
				}
			}
			
        } catch (IOException e) {
			e.printStackTrace();
		} finally {  
            try {  
                // 关闭连接,释放资源  
                if (response != null) {  
                    response.close();  
                }  
                if (httpClient != null) {  
                    httpClient.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
		}
        return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = getPixivCookie();
        byte bt[] = new byte[1024];  
        bt = str.getBytes();
        LOGGER.info("开始把Cookie存文件");
        File directory = new File(Constants.COOKIE_PATH);
		File file = new File(Constants.COOKIE_PATH + "/PivixCookie.dat");
		try {  
			if(!directory.exists()) {
				directory.mkdirs();
			}
			if(!file.exists()) {
	            file.createNewFile();
			}
		} catch (IOException e) {  
            e.printStackTrace();  
        }  
		BufferedOutputStream output = null;
		try {
			
			output = new BufferedOutputStream(new FileOutputStream(file));
			output.write(bt);  
			output.flush(); 
			LOGGER.info("Cookie存储成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
