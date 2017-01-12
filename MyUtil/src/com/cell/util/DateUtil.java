package com.cell.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	/**
	 * 获取系统当前时间（零点）
	 */
	public static Date getCurrentTimeForZero() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		Date zeroTime = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);// 设置年
		gc.set(Calendar.MONTH, month);// 这里0是1月..以此向后推
		gc.set(Calendar.DATE, day);// 设置天
		gc.set(Calendar.HOUR_OF_DAY, 0);// 设置小时
		gc.set(Calendar.MINUTE, 0);// 设置分
		gc.set(Calendar.SECOND, 0);// 设置秒
		gc.set(Calendar.MILLISECOND, 0);// 设置毫秒
		zeroTime = gc.getTime();
		return zeroTime;
	}
	
	/**
	 * 设置时间的时分秒为零
	 */
	public static Date setDayTimeZero(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		time = cal.getTime();
		return time;
	}

	/**
	 * 获取时间前一天
	 */
	public static Date getBeforeDate(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, -1);
		Date timeBefore = cal.getTime();
		time = setDayTimeZero(time);
		return timeBefore;
	}

	/**
	 * 获取时间后一天
	 */
	public static Date getAfterDate(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, 1);
		Date timeAfter = cal.getTime();
		time = setDayTimeZero(time);
		return timeAfter;
	}
	
	/**
	 * 获取时间当前月的第一天
	 */
	public static Date getCurrentMonthFirstDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,1);
		Date time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取时间前一月的第一天
	 */
	public static Date getBeforeMonthFirstDate(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH,-1);
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取时间前一月的最后一天
	 */
	public static Date getBeforeMonthLastDate(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH,-1);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取时间月份的第一天
	 */
	public static Date getFirstDate(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH,1);
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取时间月份的最后一天
	 */
	public static Date getLastDate(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取时间月份的下个月第一天
	 */
	public static Date getNextMonthFirstDate(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH, +1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	/**
	 * 获取当前时间后一月的第一天
	 */
	public static Date getAfterwardsMonthFirstDate(){
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH,+1);
		time = cal.getTime();
		time = setDayTimeZero(time);
		return time;
	}
	
	 /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某月的第一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
    
     Calendar cal = Calendar.getInstance();
    
     cal.set(Calendar.YEAR, year);
    
     cal.set(Calendar.MONTH, month-1);
    
     cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
    
    
     return cal.getTime();
    }
     
    /**
     * 得到某年某月的最后一天
     * 
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
    
     Calendar cal = Calendar.getInstance();
    
     cal.set(Calendar.YEAR, year);
    
     cal.set(Calendar.MONTH, month-1);
    
      cal.set(Calendar.DAY_OF_MONTH, 1);
     int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
     cal.set(Calendar.DAY_OF_MONTH, value);
    
     return cal.getTime();
    
    }

	/**
	 * 比较时间前后 true:时间1大于时间2 false:时间1小于等于时间2
	 */
	public static boolean compareDate(Date time1, Date time2) {
		if (time1.getTime() > time2.getTime()) {
			return true;
		} else {
			return false;
		}
	}

	/**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                     calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

	
	
	/**
	 * 为对象设置指定的时分秒
	 */
	public static Date setDayTime(Date time, int hour, int min, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		time = cal.getTime();
		return time;
	}
	
	/**
	 * 将时间对象转换成相应格式的字符串
	 * @param time 时间对象
	 * @param format 格式
	 * @return
	 */
	public static String dateToStringWithFormat(Date time,String format){
		String strTime = null;
		try{
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			strTime = myFormatter.format(time);
			return strTime;
		}
		catch (Exception e) {
			return strTime;
		}
	}
	
	/**
	 * 将时间字符串按照对应格式转化成时间对象
	 * @param strTime 时间字符串
	 * @param format 格式
	 * @return
	 */
	public static Date stringToDateWithFormat(String strTime,String format){
		Date time = null;
		try{
			SimpleDateFormat myFormatter = new SimpleDateFormat(format);
			time = myFormatter.parse(strTime);
			return time;
		}
		catch (Exception e) {
			return time;
		}
	}
	
	/**
	 * 根据指定年月获取该月份有多少天
	 * @param month 指定年月
	 * @return
	 */
	public static int getDayForMonth(String month){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
	    String source = month;
	    try {
	      Date date = format.parse(source);
	      Calendar calendar = new GregorianCalendar();
	      calendar.setTime(date);
	      return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    } catch (ParseException e) {
	      e.printStackTrace();
	      return 0;
	    }
	}
	
	/**
	 * 获取一个月后的当前时间
	 * @return 
	 */
	public static Date getDayForNextMonth(){
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH,+1);
		time = cal.getTime();
		return time;
	}
	
	/**
	 * 获取下个月的第一天
	 * @return 
	 */
	public static Date getNextMonthForOne(){
		Date time = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH,+1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		time = cal.getTime();
		return time;
	}
	
	/**
	 * 获取指定时间一个月后的时间
	 * @return 
	 */
	public static Date getDayForNextMonthByTime(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MONTH,+1);
		time = cal.getTime();
		return time;
	}
	
	/**
	 * 比较时间差(天数)
	 */
	public static Integer getDateDifference(Date startDate, Date endDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			startDate=sdf.parse(sdf.format(startDate));  
			endDate=sdf.parse(sdf.format(endDate)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();    
        cal.setTime(startDate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(endDate);    
        long time2 = cal.getTimeInMillis();         
        long betweenDays=(time2-time1)/(1000*3600*24); 
		return (int) Math.abs(betweenDays);
	}
	
	/**
	 * 比较时间差(月数)
	 */
	public static Integer getDateDifferenceByMonth(Date startDate, Date endDate){
		Integer difference = null;
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(startDate);
		cal2.setTime(endDate);
		Integer year = cal.get(Calendar.YEAR);
		Integer month = cal.get(Calendar.MONTH);
		Integer year2 = cal2.get(Calendar.YEAR);
		Integer month2 = cal2.get(Calendar.MONTH);
		difference = ((year2 - year) * 12) + (month2 - month);
		return difference;
	}
	
	/**
	 * 当前时间推前N天
	 * @param dayCount 天数
	 * @return
	 */
	public static Date getBeforeDate(Date time, Integer dayCount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - dayCount);
		return cal.getTime();
	}
	
	/**
	 * 当前时间推后N天
	 * @param dayCount 天数
	 * @return
	 */
	public static Date getAfterDate(Date time, Integer dayCount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + dayCount);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的末尾时间(23点59分59秒)
	 * @param time
	 * @return
	 */
	public static Date getEndOfDate(Date time){
		Date afterTime = getAfterDate(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(afterTime);
		cal.add(Calendar.SECOND, -1);
		Date endTime = cal.getTime();
		return endTime;
	}
	
	public static Date getFirstDateByYear(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date getNextYear(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	// 根据出生日期计算年龄
	public static int getAge(Date birthDate) {

		if (birthDate == null)
			throw new RuntimeException("出生日期不能为null");

		int age = 0;

		Date now = new Date();

		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(birthDate);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(birthDate);
		String this_month = format_M.format(now);

		// 初步，估算
		age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

		// 如果未到出生月份，则age - 1
		if (this_month.compareTo(birth_month) < 0)
			age -= 1;
		if (age < 0)
			age = 0;
		return age;
	}
	
}
