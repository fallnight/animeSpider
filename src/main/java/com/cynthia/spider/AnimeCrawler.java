package com.cynthia.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class AnimeCrawler {

	Request request = new Request();
	/*@Qualifier("AnimePipeline")
	@Autowired
	private PageProcessor animePipeline;*/
	
	@Qualifier("VideoPipeline")
	@Autowired
	private PageProcessor videoPipeline;
	
	public void animeCrawl() {
		
		request.putExtra("type", "douga-mad");
		request.setUrl("http://www.bilibili.com/video/douga-mad-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	
	public void mmdCrawl() {
		request.putExtra("type", "douga-mmd");
		request.setUrl("http://www.bilibili.com/video/douga-mmd-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	
	public void voiceCrawl() {
		request.putExtra("type", "douga-voice");
		request.setUrl("http://www.bilibili.com/video/douga-voice-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	
	public void elseCrawl() {
		request.putExtra("type", "douga-else");
		request.setUrl("http://www.bilibili.com/video/douga-else-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//连载动画
	public void twoCrawl() {
		request.putExtra("type", "bangumi-two");
		request.setUrl("http://www.bilibili.com/video/bangumi-two-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//完结动画
	public void twoelementCrawl() {
		request.putExtra("type", "part-twoelement");
		request.setUrl("http://www.bilibili.com/video/part-twoelement-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//国产动画
	public void chineseCrawl() {
		request.putExtra("type", "bangumi_chinese");
		request.setUrl("http://www.bilibili.com/video/bangumi_chinese-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//动画资讯
	public void informationCrawl() {
		request.putExtra("type", "douga-else-information");
		request.setUrl("http://www.bilibili.com/video/douga-else-information-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//官方延伸
	public void officalCrawl() {
		request.putExtra("type", "bagumi_offical");
		request.setUrl("http://www.bilibili.com/video/bagumi_offical-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//翻唱
	public void coverCrawl() {
		request.putExtra("type", "music-Cover");
		request.setUrl("http://www.bilibili.com/video/music-Cover-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//V家
	public void vocaloidCrawl() {
		request.putExtra("type", "music-vocaloid");
		request.setUrl("http://www.bilibili.com/video/music-vocaloid-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//乐器演奏
	public void performCrawl() {
		request.putExtra("type", "music-perform");
		request.setUrl("http://www.bilibili.com/video/music-perform-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//三次元音乐
	public void coordinateCrawl() {
		request.putExtra("type", "music-coordinate");
		request.setUrl("http://www.bilibili.com/video/music-coordinate-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//同人音乐
	public void musicvideoCrawl() {
		request.putExtra("type", "music-video");
		request.setUrl("http://www.bilibili.com/video/music-video-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//oped
	public void opedCrawl() {
		request.putExtra("type", "music-oped");
		request.setUrl("http://www.bilibili.com/video/music-oped-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//音乐选集
	public void collectionCrawl() {
		request.putExtra("type", "music-collection");
		request.setUrl("http://www.bilibili.com/video/music-collection-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//宅舞
	public void danceCrawl() {
		request.putExtra("type", "dance");
		request.setUrl("http://www.bilibili.com/video/dance-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//三次元舞蹈
	public void tdDanceCrawl() {
		request.putExtra("type", "three-dimension-dance");
		request.setUrl("http://www.bilibili.com/video/three-dimension-dance-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	
	//舞蹈教程
	public void danceDemoCrawl() {
		request.putExtra("type", "dance-demo");
		request.setUrl("http://www.bilibili.com/video/dance-demo-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//单机联机
	public void gameCrawl() {
		request.putExtra("type", "game-video");
		request.setUrl("http://www.bilibili.com/video/game-video-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//网游电竞
	public void networkGameCrawl() {
		request.putExtra("type", "game-ctary-network");
		request.setUrl("http://www.bilibili.com/video/game-ctary-network-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//音游
	public void musicGameCrawl() {
		request.putExtra("type", "music-game");
		request.setUrl("http://www.bilibili.com/video/music-game-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//mugen游戏
	public void mugenCrawl() {
		request.putExtra("type", "game-mugen");
		request.setUrl("http://www.bilibili.com/video/game-mugen-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//游戏素材做的MV
	public void gmvCrawl() {
		request.putExtra("type", "gmv");
		request.setUrl("http://www.bilibili.com/video/gmv-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//纪录片
	public void techScienceCrawl() {
		request.putExtra("type", "tech-popular-science");
		request.setUrl("http://www.bilibili.com/video/tech-popular-science-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//趣味科普人文
	public void techfunCrawl() {
		request.putExtra("type", "tech-fun");
		request.setUrl("http://www.bilibili.com/video/tech-fun-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//野生技术协会
	public void techwildCrawl() {
		request.putExtra("type", "tech-wild");
		request.setUrl("http://www.bilibili.com/video/tech-wild-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//演讲公开课
	public void speechCourseCrawl() {
		request.putExtra("type", "speech-course");
		request.setUrl("http://www.bilibili.com/video/speech-course-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//军事相关
	public void militaryCrawl() {
		request.putExtra("type", "tech-future-military");
		request.setUrl("http://www.bilibili.com/video/tech-future-military-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//数码
	public void digitalCrawl() {
		request.putExtra("type", "tech-future-digital");
		request.setUrl("http://www.bilibili.com/video/tech-future-digital-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//机械相关
	public void otherCrawl() {
		request.putExtra("type", "tech-future-other");
		request.setUrl("http://www.bilibili.com/video/tech-future-other-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//搞笑
	public void funnyCrawl() {
		request.putExtra("type", "ent_funny");
		request.setUrl("http://www.bilibili.com/video/ent_funny-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//生活
	public void lifeCrawl() {
		request.putExtra("type", "ent-life");
		request.setUrl("http://www.bilibili.com/video/ent-life-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//动物
	public void animaCrawl() {
		request.putExtra("type", "ent-animal");
		request.setUrl("http://www.bilibili.com/video/ent-animal-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//美食
	public void foodCrawl() {
		request.putExtra("type", "ent-food");
		request.setUrl("http://www.bilibili.com/video/ent-food-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//综艺
	public void varietyCrawl() {
		request.putExtra("type", "ent-variety");
		request.setUrl("http://www.bilibili.com/video/ent-variety-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//娱乐圈
	public void circleCrawl() {
		request.putExtra("type", "ent-circle");
		request.setUrl("http://www.bilibili.com/video/ent-circle-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//鬼畜调教
	public void kichikuCrawl() {
		request.putExtra("type", "ent-Kichiku");
		request.setUrl("http://www.bilibili.com/video/ent-Kichiku-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//音mad
	public void dougakichikuCrawl() {
		request.putExtra("type", "douga-kichiku");
		request.setUrl("http://www.bilibili.com/video/douga-kichiku-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//人力Vocaloid
	public void manualVocaloidCrawl() {
		request.putExtra("type", "kichiku-manual_vocaloid");
		request.setUrl("http://www.bilibili.com/video/kichiku-manual_vocaloid-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//鬼畜教程
	public void kichikuCourseCrawl() {
		request.putExtra("type", "kichiku-course");
		request.setUrl("http://www.bilibili.com/video/kichiku-course-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//电影相关
	public void movieCrawl() {
		request.putExtra("type", "movie-presentation");
		request.setUrl("http://www.bilibili.com/video/movie-presentation-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//短片
	public void micromovieCrawl() {
		request.putExtra("type", "tv-micromovie");
		request.setUrl("http://www.bilibili.com/video/tv-micromovie-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//欧美电影
	public void westMovieCrawl() {
		request.putExtra("type", "movie_west");
		request.setUrl("http://www.bilibili.com/video/movie_west-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//日本电影
	public void movieJapanCrawl() {
		request.putExtra("type", "movie_japan");
		request.setUrl("http://www.bilibili.com/video/movie_japan-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//国产电影
	public void movieChineseCrawl() {
		request.putExtra("type", "movie_chinese");
		request.setUrl("http://www.bilibili.com/video/movie_chinese-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//其他国家
	public void moviemovieCrawl() {
		request.putExtra("type", "movie-movie");
		request.setUrl("http://www.bilibili.com/video/movie-movie-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//连载剧集
	public void soapThreeCrawl() {
		request.putExtra("type", "soap-three");
		request.setUrl("http://www.bilibili.com/video/soap-three-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	//完结剧集
	public void tvDramaCrawl() {
		request.putExtra("type", "tv-drama");
		request.setUrl("http://www.bilibili.com/video/tv-drama-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(15).run();
    }
	
	//特摄。布袋
	public void tvSfxCrawl() {
		request.putExtra("type", "tv-sfx");
		request.setUrl("http://www.bilibili.com/video/tv-sfx-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(20).run();
    }
	//电视剧相关
	public void tvPresentationCrawl() {
		request.putExtra("type", "tv-presentation");
		request.setUrl("http://www.bilibili.com/video/tv-presentation-1.html");
		Spider.create(videoPipeline)
						.addRequest(request)
						.thread(10).run();
    }
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final AnimeCrawler animeCrawler = applicationContext.getBean(AnimeCrawler.class);
        animeCrawler.animeCrawl();
//        animeCrawler.mmdCrawl();
//        animeCrawler.voiceCrawl();
//        animeCrawler.elseCrawl();
//        animeCrawler.twoCrawl();
//        animeCrawler.twoelementCrawl();
//        animeCrawler.chineseCrawl();
//        animeCrawler.informationCrawl();
//        animeCrawler.officalCrawl();
//        animeCrawler.coverCrawl();
//        animeCrawler.vocaloidCrawl();
//        animeCrawler.performCrawl();
//        animeCrawler.coordinateCrawl();
//        animeCrawler.musicvideoCrawl();
//        animeCrawler.opedCrawl();
//        animeCrawler.collectionCrawl();
//        animeCrawler.danceCrawl();
//        animeCrawler.tdDanceCrawl();
//        animeCrawler.danceDemoCrawl();
//        animeCrawler.gameCrawl();
//        animeCrawler.networkGameCrawl();
//        animeCrawler.musicGameCrawl();
//        animeCrawler.mugenCrawl();
//        animeCrawler.gmvCrawl();
//        animeCrawler.techScienceCrawl();
//        animeCrawler.techfunCrawl();
//        animeCrawler.techwildCrawl();
//        animeCrawler.speechCourseCrawl();
//        animeCrawler.militaryCrawl();
//        animeCrawler.digitalCrawl();
//        animeCrawler.otherCrawl();
//        animeCrawler.funnyCrawl();
//        animeCrawler.lifeCrawl();
//        animeCrawler.animaCrawl();
//        animeCrawler.foodCrawl();
//        animeCrawler.varietyCrawl();
//        animeCrawler.circleCrawl();
//        animeCrawler.kichikuCrawl();
//        animeCrawler.dougakichikuCrawl();
//        animeCrawler.manualVocaloidCrawl();
//        animeCrawler.kichikuCourseCrawl();
//        animeCrawler.movieCrawl();
//        animeCrawler.micromovieCrawl();
//        animeCrawler.westMovieCrawl();
//        animeCrawler.movieJapanCrawl();
//        animeCrawler.movieChineseCrawl();
//        animeCrawler.moviemovieCrawl();
//        animeCrawler.soapThreeCrawl();
//        animeCrawler.tvDramaCrawl();
//        animeCrawler.tvSfxCrawl();
//        animeCrawler.tvPresentationCrawl();
	}
}
