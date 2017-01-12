package com.sunshine.pm2d5.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constant {

	// 定义出错消息码
	public static final int CITYDATA_OK = 0;
	public static final int NET_ERROR = -1;
	public static final int ADDR_ERROR = -2;
	
	//定义记录LOG的文件名称
	public static final String LOG_FILENAME = "crawer_log.txt";

	//根据设定的日期计算该日期以后某天的日期
	public static String getSpecialDate(String oriDate, int gap)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		date = new SimpleDateFormat("yyyy-MM-dd").parse(oriDate);
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + gap);
		String result = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime());
		return result;
	}
	
	/**
	 * 获取当前时刻的详细的时间信息，用于插入数据库用
	 * @return
	 */
	public static String getCurrentTime (){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	/**
	 * 将数据写入文件
	 * @param log
	 */
	public static void log2File (String log) throws Exception{
		File file = new File (Constant.LOG_FILENAME);
		if (!file.exists()){
			file.mkdir();
		}
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		FileWriter writer = new FileWriter(file, true);
		writer.write(log+"\n");
		writer.flush();
		writer.close();
	}

}
