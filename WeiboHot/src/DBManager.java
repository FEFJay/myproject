import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

	// 创建静态全局变量
	static Connection conn;
	static Statement st;
	static String tmp;//插入微博的内容的时候的缓存变量
	
	public static void main (String args[]){
		
	}
	
	/* 获取数据库连接的函数*/  
    public static Connection getConnection() {  
        Connection con = null;  //创建用于连接数据库的Connection对象  
        try {  
            Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动  
              
            con = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/weibo_hot", "root", "123456");// 创建数据连接  
  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println("数据库连接失败" + e.getMessage());  
        }  
        return con; //返回所建立的数据库连接  
    }  
    

    public static int insertWeiboHotContent(String tableName,String date,String url,String weibo_content) {
		int count = 0;
		try {
			conn = getConnection();
			String sqlString = "insert into "+tableName+"(time,url,weibo_content) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sqlString);
			pst.setString(1, date);
			pst.setString(2, url);
			pst.setString(3, weibo_content);
			count = pst.executeUpdate();
			pst.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("数据库插入异常！"+e.getMessage());
		}
		return count;
	}
}
	
  
	
	
    



