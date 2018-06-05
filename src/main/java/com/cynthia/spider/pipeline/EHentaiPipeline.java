package com.cynthia.spider.pipeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.EhentaiDao;
import com.cynthia.spider.model.Ehentai;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component("EHentaiPipeline")
public class EHentaiPipeline implements PageProcessor{
	
	
	@Resource
	private EhentaiDao ehentaiDao;
	
	private Site site = Site.me().setSleepTime(1500).setTimeOut(3000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0")
			.setCycleRetryTimes(5);
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public void process(Page page) {
		if(page.getUrl().toString().equals("https://e-hentai.org/")) {
			for(int i = 0; i < 16051; i ++) { 
				page.addTargetRequest("https://e-hentai.org/?page=" + i);
			}
		}
		if(page.getUrl().regex("https://e-hentai.org/\\?page.*").match()) {
			for (int i = 2; i < 27; i++) {
				if(i != 14) {
					String url = page.getHtml().xpath("//table[@class='itg']/tbody/tr[" + i + "]//div[@class='it5']/a/@href").toString();
					page.addTargetRequest(url);
					Ehentai ehentai = new Ehentai();
					String coverUrl = page.getHtml().xpath("//table[@class='itg']/tbody/tr[" + i + "]//div[@class='it2']/img/@src").toString();
					String coverbaseUrl = page.getHtml().xpath("//table[@class='itg']/tbody/tr[" + i + "]//div[@class='it2']/text()").toString();
					if(StringUtils.isNotBlank(coverUrl)) {
						ehentai.setCoverUrl(coverUrl);
					} else {
						String[] coverUrlArray = coverbaseUrl.split("~");
						ehentai.setCoverUrl("https://" + coverUrlArray[1] + "/" + coverUrlArray[2]);
					}
					ehentai.setImageUrl(url);
					ehentai.setTitle(page.getHtml().xpath("//table[@class='itg']/tbody/tr[" + i + "]//div[@class='it5']/a/text()").toString());
 					int first = url.indexOf("/g/") + 3;
					int last = getCharacterPosition(url,"/",5);
					ehentai.setImageId(Integer.parseInt(url.substring(first, last)));
					ehentaiDao.add(ehentai);
				}
			}
		}
		
 		if(page.getUrl().regex("https://e-hentai.org/g/.*").match()) {
			Ehentai ehentai = new Ehentai();
			
			try {
				ehentai.setPublished(sdf.parse(page.getHtml().xpath("//div[@id='gdd']/table/tbody/tr[1]//td[@class='gdt2']/text()").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ehentai.setFileSize(page.getHtml().xpath("//div[@id='gdd']/table/tbody/tr[5]//td[@class='gdt2']/text()").toString());
			ehentai.setLength(Integer.parseInt(page.getHtml().xpath("//div[@id='gdd']/table/tbody/tr[6]//td[@class='gdt2']/text()").toString().split(" ")[0]));
			String favorited = page.getHtml().xpath("//div[@id='gdd']/table/tbody/tr[7]//td[@class='gdt2']/text()").toString().split(" ")[0];
			
			ehentai.setFavorited("Once".equals(favorited) ? 1 : Integer.parseInt(favorited));
			ehentai.setRatingAverage(page.getHtml().xpath("//div[@id='gdr']/table/tbody/tr[2]/td/text()").toString().split(" ")[1]);
			ehentai.setRatingNum(Integer.parseInt(page.getHtml().xpath("//div[@id='gdr']/table/tbody/tr[1]//td[@id='grt3']/span/text()").toString()));
			ehentai.setAlias(page.getHtml().xpath("//h1[@id='gj']/text()").toString());
			String typeUrl = page.getHtml().xpath("//div[@id='gdc']/a/@href").toString();
			ehentai.setTypes(typeUrl.substring(21));
			String url = page.getUrl().toString();
			int first = url.indexOf("/g/") + 3;
			int last = getCharacterPosition(url,"/",5);
			ehentai.setImageId(Integer.parseInt(url.substring(first, last)));
			ehentaiDao.update(ehentai);
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static int getCharacterPosition(String string, String reg, int times){  
        //这里是获取"/"符号的位置  
        Matcher slashMatcher = Pattern.compile(reg).matcher(string);  
        int mIdx = 0;  
        while(slashMatcher.find()) {  
           mIdx++;  
           //当"/"符号第三次出现的位置  
           if(mIdx == times){  
              break;  
           }  
        }  
        return slashMatcher.start();  
     }
	
}
