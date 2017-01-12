package com.sunshine.pm2d5.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sunshine.pm2d5.beans.CrawerBeans;
import com.sunshine.pm2d5.utils.Constant;

/**
 * 数据库管理类
 * 
 * @author Ivsunshine
 * 
 */
public class DBManager {

	// 创建静态全局变量
	static Connection conn;
	static Statement st;
	
	public static void main (String args[]){
		
	}
	
	/* 获取数据库连接的函数*/  
    public static Connection getConnection() {  
        Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动  
              
            con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/weibo_new", "root", "123456");// 创建数据连接  
//		            "jdbc:mysql://115.156.216.102:3306/weibo_new", "root", "zhongmingmao");// 创建数据连接  
        } catch (Exception e) {  
            System.out.println("数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
    }  
    
    /**
     * 判断user_id的用户是否存在dbName的数据表中
     * @param dbName
     * @param user_id
     * @return
     * @throws SQLException
     */
    public static boolean isPm2d5RecordHave(String tableName, String erea_city, String pm2d5_date) throws SQLException{
    	/*if (bean == null || bean.getErea_city() == null || bean.getPm2d5_date() == null){
    		return false;
    	}*///这个地方没有必要这样判断
    	conn = getConnection(); // 首先要获取连接，即连接到数据库
    	String sql = "select pm2d5 from " + tableName + " where erea_city = '"+erea_city+"' and pm2d5_date = '"+pm2d5_date+"'"; // 查询数据的sql语句
		st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
		ResultSet rs = st.executeQuery(sql);
		boolean result = false;
		while (rs.next()){
			result = true;
		}
    	return result;
    }
	
	/**
	 * 将某一个爬取的页面的城市的所有信息添加到数据库中
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public static int[] insertPm2d5CrawerInfo (CrawerBeans bean) throws Exception{
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		// 关闭事务自动提交  
//		conn.setAutoCommit(false);
		String sql = "insert into pm2d5 (erea_city, pm2d5, pm2d5_date, time_crawer) values (?,?,?,?)";
		PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
		int[] count = null;//默认值0，1表示插入成功，2表示已经存在该值!
		
		//采用批量插入的方式
		String erea_city = bean.getCity_name();
		String time_start = bean.getStart_time();
		String[] pm2d5datas = bean.getData();
		for (int i = 0; i < pm2d5datas.length; i ++){
			//如果对象为空
			if (bean.getCity_name() == "" || bean.getCity_name() == null || pm2d5datas[i] == ""){
				System.out.println(erea_city + "----" + i + "数据不完整，请重新检查!");
				Constant.log2File(erea_city + "----" + i + "数据不完整，请重新检查!");
				continue;
			}
			//检查该城市的该数据是否爬取过，构造城市的名称和当前的时期
			String pm2d5Date = Constant.getSpecialDate(time_start, i);
			if (isPm2d5RecordHave("pm2d5", bean.getCity_name(), pm2d5Date)){
				System.out.println(erea_city + "----" + pm2d5Date + "的数据已经存在!");
				Constant.log2File(erea_city + "----" + pm2d5Date + "的数据已经存在!");
				continue;
			}
			//开始构造SQL语句
			pst.setString(1, erea_city);
			pst.setFloat(2, Float.parseFloat(pm2d5datas[i]));
			pst.setString(3, pm2d5Date);
			pst.setString(4, Constant.getCurrentTime());
			// 把一个SQL命令加入命令列表  
			pst.addBatch();
		}
		// 执行批量更新  
		count = pst.executeBatch();  
//		pst.executeBatch();  
		// 语句执行完毕，提交本事务
//		conn.commit();
		
		pst.close();
		conn.close();
		int tmp = 0;
		for (int j = 0; j < count.length; j ++){
			if (count[j] == 0){
				tmp ++;
			}
		}
		if (tmp > 0){
			System.out.println(erea_city + "----" + "有" + tmp + "条数据没有插入成功!");
		}
		System.out.println(erea_city + "----" + "数据已插入成功!");
		Constant.log2File(erea_city + "----" + "数据已插入成功!");
		
		return count;
	}
	
	//返回所有城市的列表
	public static ArrayList<String> getAllCityList () throws Exception{
		ArrayList<String> cityList = new ArrayList<String>();
		conn = getConnection(); // 首先要获取连接，即连接到数据库
		String sql = "select erea_city from city_list";
		st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()){
			cityList.add(rs.getString(1));
		}
		
		return cityList;
	}
    
}
