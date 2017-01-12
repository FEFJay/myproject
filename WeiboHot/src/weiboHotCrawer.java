
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class weiboHotCrawer {

	private static final String USER_AGENT = "Mozilla";
	private static final int TIME_OUT = 5000;
	
	public static void main(String[] args) {
		long startTime = 20130901;
		long endTime = 20140216;
		if(startTime<=endTime){
			long urlTime = startTime;
			String urlTimeString = String.valueOf(urlTime);
			while(urlTime<=endTime){
				String urlString = "http://hot.weibo.com/daily/"+urlTimeString+"?v=9999";
				getweibo(urlString,urlTimeString);
				urlTime=getNewTime(urlTimeString);
				System.out.println(urlTime);
				urlTimeString = String.valueOf(urlTime);
				
			}
		}
		
	}
	
	public static long getNewTime (String time1){
		long data=0;
        try {
             DateFormat df = new SimpleDateFormat("yyyyMMdd");
             data =df.parse(time1).getTime()+86400000;
             String newtimeString = df.format(data);
             data = Long.parseLong(newtimeString);
            // System.out.println(data);
       } catch (Exception e) {
             e.printStackTrace();
       }
        return data;
}
	public static void getweibo(String urlstring,String date) {
		try {
			String url = urlstring;
			String date1 = date;
			String weiboContent = "";
			int count = 0;
			Document document = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIME_OUT).get();
			
			Elements elements = document.select("div.WB_detail");
			
			for (Element element : elements) {
				weiboContent = element.select("div.WB_text").text();
				count = DBManager.insertWeiboHotContent("weibo_hot", date1, url, weiboContent);
				System.out.println(weiboContent);
				System.out.println("*************************************************************");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

