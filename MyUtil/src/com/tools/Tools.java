package com.tools;


import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具
 * 
 * <p>字段串的操作,时间操作,MD5加密,上传文件
 */
public class Tools {

	/**
	 * md5加密
	 * @param x
	 * @return 加密后的字符串
	 */
	public static String md5(String x) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(x.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
			}
			return result;
		} catch (Exception e) {
			System.out.println("Tools.md5 加密[" + x + "]失败");
			return null;
		}
	}
	
	/**
	 * 格式化时间
	 * @param dateTime  要格式化的时间
	 * @param pattern   格式化的样式
	 * @return 已格式化的时间
	 */
	public static String formatDateTime(Date dateTime, String pattern){
		SimpleDateFormat dateFmt = new SimpleDateFormat(pattern);
		return dateTime==null?"":dateFmt.format(dateTime);
	}
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return 返回"2007"数据格式的字符串
	 */
	public static String miniTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy");
	}
	
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return 返回"2007-09-10"数据格式的字符串
	 */
	public static String shortTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd");
	}
	
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return  返回"2007-09-10 16:09"数据格式的字符串
	 */
	public static String middleTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd HH:mm");
	}
	
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return 返回"2007-09-10 16:09:00"数据格式的字符串
	 */
	public static String longTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return 返回"20070910160900"数据格式的字符串
	 */
	public static String minLongTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyyMMddHHmmss");
	}
	
	/**
	 * 取得时间
	 * @param dateTime  
	 * @return 返回"2007年09月10号 16点09分00秒"数据格式的字符串
	 */
	public static String longZhTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy年MM月dd号 HH点mm分ss秒");
	}
	
	/**
	 * 取得时间
	 * @param dateTime 
	 * @return 返回"2007/09/10 16:09:00.000"数据格式的字符串
	 */
	public static String bigLongTime(Date dateTime) {
		return formatDateTime(dateTime,"yyyy/MM/dd HH:mm:ss.SSS");
	}
	
	/**
	 * 时间+-天数 :要得到的时间
	 * @param d      时间
	 * @param offset 天数
	 * @param bool  true天数加false天数减
	 * @return  
	 */
	public static Date changeDay(Date d,int offset,boolean bool){   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(d);
	    if(bool){
	    	calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset)); 
	    }else{
	    	calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) - offset));   
	    }    
	    return calendar.getTime();   
	  }
	/**
	 * 时间+-天数 :要得到的时间
	 * @param d      时间
	 * @param offset 天数
	 * @param bool  true天数加false天数减
	 * @return  
	 */
	public static Timestamp changeDay(Timestamp d,int offset,boolean bool){   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(d);
	    if(bool){
	    	calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) + offset)); 
	    }else{
	    	calendar.set(Calendar.DAY_OF_YEAR,(calendar.get(Calendar.DAY_OF_YEAR) - offset));   
	    }    
	    return new Timestamp(calendar.getTimeInMillis());   
	  }
	/**
	 * 时间+-多少年 :要得到的时间
	 * @param d      时间
	 * @param offset 年数
	 * @param bool  true年数加false年数减
	 * @return  
	 */
	public static Date changeYear(Date d,int offset,boolean bool){   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(d);
	    if(bool){
	    	calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) + offset)); 
	    }else{
	    	calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) - offset));   
	    }    
	    return calendar.getTime();   
	  }
	/**
	 * 时间+-多少年 :要得到的时间
	 * @param d      时间
	 * @param offset 年数
	 * @param bool  true年数加false年数减
	 * @return  
	 */
	public static Timestamp changeYear(Timestamp d,int offset,boolean bool){   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.setTime(d);
	    if(bool){
	    	calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) + offset)); 
	    }else{
	    	calendar.set(Calendar.YEAR,(calendar.get(Calendar.YEAR) - offset));   
	    }    
	    return new Timestamp(calendar.getTimeInMillis());   
	  }
	
	
	/**
	 * 字符串是否可以转化成Double形式
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str){
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$");  
		return pattern.matcher(str).matches();
	}
	/**
	 * 是否可以转化为整数
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str){
	    Pattern pattern = Pattern.compile("[[-\\+]?0-9]*");
	    return pattern.matcher(str).matches();  
	} 
	
	/**
	 * 是否可以转化为整数加字符串
	 * @param str
	 * @return
	 */
	public static boolean isStr(String str){
		Pattern pattern = Pattern.compile("[-_a-zA-Z0-9]*");
	    return pattern.matcher(str).matches();  
	} 
	
	/**
	 * 是否可以转化为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
	    return isInteger(str) || isDouble(str);  
	} 
	
	/**
	 * 取得结束的时间
	 * 如果参数为2007，则返回2007-12-31 23:59:59
	 * 如果参数为2007-08 ，则返回2007-08-31 23:59:59
	 * 如果参数为2007-09 ，则返回2007-09-30 23:59:59
	 * 如果参数为2007-09-09 ，则返回2007-09-09 23:59:59
	 * @param time yyyy yyyy-MM yyyy-MM-dd形式
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeEnd(final String time){
		Calendar timeEnd = Calendar.getInstance();   
		if(time!=null){
			if(time.length()==4){
				timeEnd.set(Calendar.YEAR, Integer.parseInt(time));
				timeEnd.set(Calendar.MONTH,11);   
				timeEnd.set(Calendar.DATE, 1);   
				timeEnd.roll(Calendar.DATE, -1);   
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);   
				timeEnd.set(Calendar.MINUTE, 59);   
				timeEnd.set(Calendar.SECOND, 59);
			}
			if(time.length()==7){
				timeEnd.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeEnd.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);   
				timeEnd.set(Calendar.DATE, 1);   
				timeEnd.roll(Calendar.DATE, -1);   
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);   
				timeEnd.set(Calendar.MINUTE, 59);   
				timeEnd.set(Calendar.SECOND, 59);
			}
			if(time.length()==10){
				timeEnd.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeEnd.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);   
				timeEnd.set(Calendar.DATE, Integer.parseInt((time.split("-"))[2]));   
				timeEnd.set(Calendar.HOUR_OF_DAY, 23);   
				timeEnd.set(Calendar.MINUTE, 59);   
				timeEnd.set(Calendar.SECOND, 59);
			}
		}
		return formatDateTime(timeEnd.getTime(),"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 取得起始的时间
	 * 如果参数为2007，则返回2007-01-01 00:00:00
	 * 如果参数为2007-09 ，则返回2007-09-01 00:00:00
	 * 如果参数为2007-09-09 ，则返回2007-09-09 00:00:00
	 * @param time yyyy yyyy-MM yyyy-MM-dd形式
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeStart(final String time){
		Calendar timeStart = Calendar.getInstance();   
		if(time!=null){
			if(time.length()==4){
				timeStart.set(Calendar.YEAR, Integer.parseInt(time));
				timeStart.set(Calendar.MONTH,0);   
				timeStart.set(Calendar.DATE, 1);   
				timeStart.set(Calendar.HOUR_OF_DAY, 0);   
				timeStart.set(Calendar.MINUTE, 0);   
				timeStart.set(Calendar.SECOND, 0);
			}
			if(time.length()==7){
				timeStart.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeStart.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);   
				timeStart.set(Calendar.DATE, 1);   
				timeStart.set(Calendar.HOUR_OF_DAY, 0);   
				timeStart.set(Calendar.MINUTE, 0);   
				timeStart.set(Calendar.SECOND, 0);
			}
			if(time.length()==10){
				timeStart.set(Calendar.YEAR, Integer.parseInt((time.split("-"))[0]));
				timeStart.set(Calendar.MONTH,Integer.parseInt((time.split("-"))[1])-1);   
				timeStart.set(Calendar.DATE, Integer.parseInt((time.split("-"))[2]));   
				timeStart.set(Calendar.HOUR_OF_DAY, 0);   
				timeStart.set(Calendar.MINUTE, 0);   
				timeStart.set(Calendar.SECOND, 0);
			}
			
		}
		return formatDateTime(timeStart.getTime(),"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 判断str是否为空或为all,成立返回false,反之返回true
	 * @param str
	 * @return
	 */
	public static boolean isActive(String str){
		return str!=null && !str.trim().equals("all") && !str.trim().equals("");
	}
	
	/**
	 * 判断str是否为空或为空字符串,成立返回false,反之返回true
	 * @param str
	 * @return
	 */
	public static boolean isNullChar(String str){
		return str!=null && !str.trim().equals("");
	}
	
	/**
	 * yyyy-MM-dd hh:mm:ss转换为date类型
	 * @param str
	 */
    public static Date getDate(String str) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        Date date = null;
        try {  
        	date = df.parse(str);  
        } catch (Exception e) {  
        	System.out.println("Tools.getDate失败");
        }  
        return date;
    }  
    
    /**
     * 数据处理,保留precision位小数
     * @param val 要处理的数字
     * @param precision 要保留的小数位数
     * @return
     */
    public static Double roundDouble(double val, int precision) {  
        Double ret = null;  
        try {  
            double factor = Math.pow(10, precision);  
            ret = Math.floor(val * factor + 0.5) / factor;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    } 
    
    /** 
	* 获得随机数字符串 
	* 
	* @param length 
	*            需要获得随机数的长度 
	* @param type 
	*            随机数的类型：'0':表示仅获得数字随机数；'1'：表示仅获得字符随机数；'2'：表示获得数字字符混合随机数 
	* @return 
	*/
	public static String getRandomStr(int length, int type) {
		String strRandom = "";
		Random rnd = new Random();
		if (length < 0) 
			length = 5;
		if ((type > 2) || (type < 0)) 
			type = 2;
		switch (type) {
		case 0:
			for (int iLoop = 0; iLoop < length; iLoop++) {
				strRandom += Integer.toString(rnd.nextInt(10));
			}
			break;
		case 1:
			for (int iLoop = 0; iLoop < length; iLoop++) {
				strRandom += Integer.toString((35 - rnd.nextInt(10)), 36);
			}
			break;
		case 2:
			for (int iLoop = 0; iLoop < length; iLoop++) {
				strRandom += Integer.toString(rnd.nextInt(36), 36);
			}
			break;
		}
		return strRandom;
	}
	
	public static boolean chkInputByRegex(String inputString,String regexString){
		Pattern p=Pattern.compile(regexString);
		Matcher m=p.matcher(inputString);
		return m.matches();		
	}
	/**
	 * 取得时间段内的年的日期集合
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static String[] getYearList(String dateFrom, String dateEnd) {
		dateFrom = dateFrom.substring(0,4);
		dateEnd = dateEnd.substring(0,4);
		int df = Integer.valueOf(dateFrom);
		int de = Integer.valueOf(dateEnd);
		List<String> dateList = new ArrayList<String>();
		for (int i = df; i <= de; i++) {
			dateList.add(""+i);
		}
		String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
        return dateArray;
	}
	/**
	 * 取得时间段内的月的日期集合
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
    public static String[] getMonthList(String dateFrom, String dateEnd) {
        //指定要解析的时间格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = new ArrayList<String>();
        //定义一些变量
        Date beginDate = null;
        Date endDate = null;
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        try {
            //将字符串parse成日期
            beginDate = f.parse(dateFrom);
            endDate = f.parse(dateEnd);
            //设置日历
            beginGC = new GregorianCalendar(); 
            beginGC.setTime(beginDate); 
            endGC = new GregorianCalendar(); 
            endGC.setTime(endDate);
            //直到两个时间相同
            while(beginGC.getTime().compareTo(endGC.getTime())<=0){
            	dateList.add(beginGC.get(Calendar.YEAR) + "-" + getM(beginGC.get(Calendar.MONTH)+1));
                //以月为单位，增加时间
                beginGC.add(Calendar.MONTH,1);
            }
            dateList.add(beginGC.get(Calendar.YEAR) + "-" + getM(beginGC.get(Calendar.MONTH)+1));
            String[] dateArray = new String[dateList.size()];
            dateList.toArray(dateArray);
            return dateArray;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String getM(int i) {
		String st = ""+i;
		st = "00".substring(st.length())+st;
		return st;
	}

	/**
	 * 取得时间段内的日的日期集合
	 * @param dateFrom
	 * @param dateEnd
	 * @return
	 */
    public static String[] getDayList(String dateFrom, String dateEnd){
    	long time = 1l;
        long perDayMilSec = 24 * 60 * 60 * 1000;
        List<String> dateList = new ArrayList<String>();
        dateList.add(dateFrom);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            try {
                time = sdf.parse(dateFrom).getTime();
                time = time + perDayMilSec;
                dateFrom = sdf.format(new Date(time));
                if (dateFrom.compareTo(dateEnd) < 0) {
                    dateList.add(dateFrom);
                } else {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        dateList.add(dateEnd);
        String[] dateArray = new String[dateList.size()];
        dateList.toArray(dateArray);
        return dateArray;

    }

    /**
     * 取得map中的真实值
     * @param map
     * @param key
     * @return
     */
    public static String getValue(Map<String, Object> map, String key) {
    	Object obj = map.get(key);
    	if(obj == null)
    		return null;
    	else if(!obj.getClass().isArray())
    		return obj.toString();
    	else
    		return Arrays.toString((Object[])obj).replace("[", "").replace("]", "");
    }
    
    public static void main(String args[]){
    }
    
}
