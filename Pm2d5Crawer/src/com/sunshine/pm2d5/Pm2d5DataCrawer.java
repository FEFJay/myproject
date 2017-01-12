package com.sunshine.pm2d5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.sunshine.pm2d5.beans.CrawerBeans;
import com.sunshine.pm2d5.db.DBManager;
import com.sunshine.pm2d5.utils.Constant;

public class Pm2d5DataCrawer {
	
	public String firstDay = "";

	public static void main(String[] args) throws Exception {
		ArrayList<String> cityList = DBManager.getAllCityList();
		for (int i = 0; i < cityList.size(); i ++){
			//将每一个中文城市展开成英文的名称，并逐个尝试登陆
			System.out.println(cityList.get(i) + "----" + "开始数据爬取!");
			Constant.log2File(cityList.get(i) + "----" + "开始数据爬取!");
			ArrayList<String> englishCity = Pinyin.getEngCities(cityList.get(i));
			for (int j = 0; j < englishCity.size(); j ++){
				System.out.println(englishCity.get(j) + "----" + "开始数据爬取!");
				Constant.log2File(englishCity.get(j) + "----" + "开始数据爬取!");
				ArrayList<String> data_crawer = getPm2d5Datas ("http://air.in365.com.cn/c_"+englishCity.get(j));
				if (!data_crawer.get(0).endsWith(String.valueOf(Constant.CITYDATA_OK))){
					//表明地址出错，则返回爬取下一个
					System.out.println(englishCity.get(j) + "----" + "是错误的，进展到下一个进行爬取!");
					Constant.log2File(englishCity.get(j) + "----" + "是错误的，进展到下一个进行爬取!");
					continue;
				}
//				0
//				海口
//				68,64,61,68,59,81,67,74,55,76,63,77,68,76,103
//				2013, 11-1, 19, 0, 0, 0
				//得到正确的数据
				String cityName = data_crawer.get(1);
				String[] pm2d5data = data_crawer.get(2).split(",");
				String[] time = data_crawer.get(3).split(",");
				String start_date = time[0] + "-" + time[1].split("-")[0].trim() + "-" + time[2].trim();
				CrawerBeans bean = new CrawerBeans(cityName, pm2d5data, start_date);
				//则因此正确的数据就应该是，一个城市，一个Float的数组，一个开始的日期，使用数据库插入即可。
				DBManager.insertPm2d5CrawerInfo(bean);
			}
		}
		System.out.println("cityList" + cityList.size() + "数据已爬取完成!");
		Constant.log2File("cityList" + cityList.size() + "数据已爬取完成!");
	}
	
	/**
	 * 如果返回的结果为空，则表明两种情况：传入的英文地址是错误的，或者IP被封了。这两种情况要不要分开进行处理呢？
	 * @param chineseName
	 * @param url
	 * @return
	 */
	public static ArrayList<String> getPm2d5Datas (String url){
		ArrayList<String> data = new ArrayList<String>();
		
		//使用HttpClient获取网页内容，并进行正则表达式的解析
		HttpClient client = new DefaultHttpClient();
		HttpResponse response;
		String html = "";
		String city_name = "";
		String datas = "";
		String time = "";
		try {
			/*
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			html = EntityUtils.toString(entity, "gbk").replace("\\r", "").replace("\\n", "").replace("\\t", "").replace("\\", "");
			get.releaseConnection();//释放连接
			*/
			HttpGet getMethod = new HttpGet(url);  
            response = client.execute(getMethod);  
            int status = response.getStatusLine().getStatusCode();
            if (status != 200){
            	//则表明地址错误了
    			data.add(0, String.valueOf(Constant.ADDR_ERROR));
            	System.out.println("网页没有显示正常内容或网络连接错误!");
            	Constant.log2File("网页没有显示正常内容或网络连接错误!");
            	return data;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line;
            StringBuffer sb = new StringBuffer();
            while(null != (line = br.readLine())){
            	sb.append(line);
            }
            br.close();
            html = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			data.add(0, String.valueOf(Constant.NET_ERROR));//如果查询到第0个元素为-1，则表明出错了。
		}
		
//		System.out.println(html);
		
		/**
		 * var cityname = '海口';
		   var data_array = [68,64,61,68,59,81,67,74,55,76,63,77,68,76,103,];
		   var pointStartTime = Date.UTC(2013, 11-1, 19, 0, 0, 0);
		 * */
		Pattern pattern = Pattern.compile("cityname.*?\'(.*?)\'.*?\\[(.*?)\\].*?\\((.*?)\\)");
 		Matcher matcher = pattern.matcher(html);
		if (matcher.find()) {
			city_name = matcher.group(1);
			datas = matcher.group(2);
			time = matcher.group(3);
			data.add(0, String.valueOf(Constant.CITYDATA_OK));
			data.add(city_name);
			data.add(datas.substring(0, datas.length() - 1));//去除,号
			data.add(time);
		}else {//如果没有找到
			//则表明地址错误了
			data.set(0, String.valueOf(Constant.ADDR_ERROR));
		}
		return data;
	}

}
