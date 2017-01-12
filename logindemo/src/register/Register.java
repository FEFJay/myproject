package register;
 import java.io.*;
 import java.util.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 import java.sql.*;
 //import com.mysql.jdbc.*;

public class Register  extends HttpServlet{ 
     private String name;
     private String pass;
     private String confirm;

  public synchronized void doPost(HttpServletRequest request,HttpServletResponse response)
                         throws ServletException,IOException
    {

       this.name=request.getParameter("account");
        this.pass=request.getParameter("password");
        this.confirm=request.getParameter("confirm");
        PrintWriter out=response.getWriter();

       //驱动程序名
        String driverName="com.mysql.jdbc.Driver";
        //数据库用户名
        String userName="root";
        //密码
        String userPasswd="123";
        //数据库名
        String dbName="database";
        //表名
        String tableName="users";
        //联结字符串
        String url="jdbc:mysql://localhost/"+dbName+"?user="+userName+"&password="+userPasswd;
       try
       {
        Class.forName("org.gjt.mm.mysql.Driver");
        Connection connection=DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
       
        String sql="SELECT * FROM "+tableName;
        ResultSet rs=statement.executeQuery(sql);
       
        while(true) 
        {
            if(rs.next())
            {
                 if(this.name.equals(rs.getString(1)))
                 {             
                    out.print("<h1>"+"User Name is in Database!");              
                    break;
                  }
            }
            else
            {
                  sql="INSERT INTO "+tableName+" VALUES('"+this.name+"','"+this.pass+"')";
                  statement.execute(sql);
                  out.print("<h1>"+"Register Successful!");
                  break;
            }
        }        
        return;
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
			System.out.println("Sorry,can`t find the Driver!");
        	e.printStackTrace();
        }
             
    }
  
   public synchronized void  doGet(HttpServletRequest request,HttpServletResponse response)
                         throws ServletException,IOException
     {
       doPost(request,response);
     }
 }
