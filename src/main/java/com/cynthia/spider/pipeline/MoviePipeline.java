package com.cynthia.spider.pipeline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynthia.spider.dao.MovieDao;
import com.cynthia.spider.model.Movie;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

@Component("MoviePipeline")
public class MoviePipeline implements PageProcessor{

	
	private static final ArrayList<Integer> TYPE_ID_LIST = new ArrayList<Integer>(Arrays.asList(146,147));
	private static int totalPage = 1000;
	private static int size = 20;
	private static int tid = 145;
	
	@Resource
	private MovieDao movieDao;
	String type;
	
	private Site site = Site.me().setSleepTime(1000).setTimeOut(10000)
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0")
			.setCycleRetryTimes(5).addHeader("Cookie", "pgv_pvi=2006729728; fts=1463052423; uTZ=-480; LIVE_BUVID=938260a30cdc74ab7f6132bd2a652a43; LIVE_BUVID__ckMd5"+
"=3d4190a5a38125aa; sid=jzj0u41e; _cnt_dyn=null; time_tracker=20160615; buvid3=EA0D31D3-06D0-4D60-96C6-62B4F91A96F111266infoc" +
"; DedeUserID=3715640; DedeUserID__ckMd5=c347fde6c1eb2c9f; SESSDATA=f7dd85ed%2C1483457115%2Cbbee030b;" +
 "ck_pv=NtOViC; SSID=VEWTzyBobZydAN1fw8_aVpXqjEGACUMw_a11nntX4HBjdNJRI3FPXtw_b0DUFK8GcYwrGEzmTpBd_axvdNtK7IxUGhYBXE_bnQKO5vu9GVQvFFRQ_c" +
"; _ver=1; _cnt_pm=0; _cnt_notify=12; rpdid=kxxoiliioqdopqoilopxw; LIVE_LOGIN_DATA=9ffb5b6cab51dac1c979fee3a42e2d4063fafc92" +
"; LIVE_LOGIN_DATA__ckMd5=89e92b86fcb8dd07; user_face=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2F7b4c23a9cc559d9910eed1c1603010f9ac748e51" +
".jpg; purl_token=bilibili_1482732658; pgv_si=s8467388416")/*.setHttpProxy(new HttpHost("124.226.169.245",8118))*/;
	
	
	
	public void process(Page page) {
		if(page.getUrl().toString().equals("http://www.baidu.com")) {
			List<Movie> movies = movieDao.findSourceUrl();
			if(movies.size() > 0) {
				List<String> urls = new ArrayList<String>();
				for (int i = 0; i < movies.size(); i++) {
					urls.add("http://api.bilibili.com/playurl?aid=" + movies.get(i).getUrl().substring(32) + "&page=1&vtype=hdmp4");
				}
				page.addTargetRequests(urls);
			}
		}
		if(page.getUrl().toString().startsWith("http://api.bilibili.com/playurl")) {
			String jsonData = page.getRawText();
			String durl = new JsonPathSelector("$.durl[0]").select(jsonData).toString();
			String url1 = "";
			if(durl.indexOf("\"url\"") != -1) {
				url1 = new JsonPathSelector("$.durl[0].url").select(jsonData).toString();
			}
			String url2 = new JsonPathSelector("$.durl[0].backup_url[0]").select(jsonData).toString();
			String url3 = new JsonPathSelector("$.durl[0].backup_url[(@.length-1)]").select(jsonData).toString();
			String url = "";
			url = checkRealUrl(url1) ? url1 : url;
			url = checkRealUrl(url2) ? url1 : url;
			url = checkRealUrl(url3) ? url1 : url;
			Movie movie = new Movie();
			movie.setUrl("http://www.bilibili.com/video/av" + page.getUrl().toString().substring(36,page.getUrl().toString().indexOf("&page")));
			movieDao.update(movie);
		} else {
			if(page.getUrl().regex(".*tid=.*").match()) {
				int pageNum = Integer.parseInt(page.getUrl().toString().substring(page.getUrl().toString().indexOf("&pn=") + 4,page.getUrl().toString().indexOf("&type=jsonp&_")));
				String jsonData = page.getRawText().substring(42,page.getRawText().lastIndexOf(")"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				if(pageNum == 1) {
					int count = Integer.parseInt(new JsonPathSelector("$.data.page.count").select(jsonData).toString());
					size = Integer.parseInt(new JsonPathSelector("$.data.page.size").select(jsonData).toString());
					totalPage = Math.round(count / size);
				}
				if(pageNum == totalPage) {
					if(TYPE_ID_LIST.size() == 0) {
						return;
					}
					for (int i = 0; i < TYPE_ID_LIST.size(); i++) {
						if(tid != TYPE_ID_LIST.get(i)) {
							tid = TYPE_ID_LIST.get(i);
							TYPE_ID_LIST.remove(i);
							break;
						}
					}
					page.addTargetRequest("http://api.bilibili.com/archive_rank/getarchiverankbypartion?callback=jQuery172006524573556990121_1482129542780&type=jsonp&tid="+ tid +"&pn=1&type=jsonp&_=1468488357906");
				}
				if(pageNum < totalPage) {
					List<String> urls = new ArrayList<String>();
					if(pageNum % 10 == 1 || pageNum == 1) {
						for (int i = 1; i <= 10; i++) {
							urls.add("http://api.bilibili.com/archive_rank/getarchiverankbypartion?callback=jQuery172006524573556990121_1482129542780&type=jsonp&tid="+ tid +"&pn=" + (pageNum + i) + "&type=jsonp&_=1468488357906");
						}
						page.addTargetRequests(urls);
					}
					 
					for (int j = 0; j < size; j++) {
						Movie movie = new Movie();
						String view = new JsonPathSelector("$.data.archives["+ j +"].stat.view").select(jsonData).toString();
						if(!"--".equals(view) && !"".equals(view) && view != null) {
							movie.setView(Integer.parseInt(view));
						}
						movie.setDanmaku(Integer.parseInt(new JsonPathSelector("$.data.archives["+ j +"].stat.danmaku").select(jsonData).toString()));
						movie.setReply(Integer.parseInt(new JsonPathSelector("$.data.archives["+ j +"].stat.reply").select(jsonData).toString()));
						movie.setFavorite(Integer.parseInt(new JsonPathSelector("$.data.archives["+ j +"].stat.favorite").select(jsonData).toString()));
						movie.setCoin(Integer.parseInt(new JsonPathSelector("$.data.archives["+ j +"].stat.coin").select(jsonData).toString()));
						movie.setShare(Integer.parseInt(new JsonPathSelector("$.data.archives["+ j +"].stat.share").select(jsonData).toString()));
						movie.setAuthor(new JsonPathSelector("$.data.archives["+ j +"].author").select(jsonData).toString());
						movie.setContent(new JsonPathSelector("$.data.archives["+ j +"].description").select(jsonData).toString());
						String date = new JsonPathSelector("$.data.archives["+ j +"].create").select(jsonData).toString();
						if(date != null && !"".equals(date) && !"--".equals(date)) {
							try {
								movie.setCreatetime(sdf.parse(date));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						movie.setTitle(new JsonPathSelector("$.data.archives["+ j +"].title").select(jsonData).toString());
						movie.setUrl("http://www.bilibili.com/video/av" + new JsonPathSelector("$.data.archives["+ j +"].aid").select(jsonData).toString());
						//page.addTargetRequest("http://www.flvcd.com/parse.php?format=high&kw=" + movie.getUrl());
						movieDao.add(movie);
					}
				}
			}
		}
	}

	private boolean checkRealUrl(String url) {
		if(!"".equals(url) && url != null && url.indexOf(".mp4") != -1) {
			return true;
		}
		return false;
	}
	public Site getSite() {
		return site;
	}
}
