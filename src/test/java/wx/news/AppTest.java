package wx.news;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	
	public static void main(String[] args) {
//		String url = "https://e-hentai.org/g/1224206/3af21c483c/";
//		int first = url.indexOf("/g/") + 3;
//		int last = getCharacterPosition(url,"/",5);
//		System.out.println(url.substring(first, last));
		
		String url = "https://e-hentai.org/doujinshi";
		System.out.println(url.substring(21));
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
