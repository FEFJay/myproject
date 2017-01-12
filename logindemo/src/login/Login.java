package login;
 import java.io.*;
 import java.util.*;
 import javax.servlet.*;
 import javax.servlet.http.*;
 
 import java.sql.*;
 //import com.mysql.jdbc.*;

public class Login  extends HttpServlet
 { 
     private String name;
     private String pass;

  public synchronized void doPost(HttpServletRequest request,HttpServletResponse response)
                         throws ServletException,IOException
     {

       this.name=request.getParameter("account");
        this.pass=request.getParameter("password");
        PrintWriter out=response.getWriter();

       //�������
         String driverName="com.mysql.jdbc.Driver";
        //��ݿ��û���
        String userName="root";
        //����
        String userPasswd="123";
        //��ݿ���
        String dbName="database";
        //����
        String tableName="users";
        //�����ַ�
        String url="jdbc:mysql://localhost:8080/"+dbName+"?user="+userName+"&password="+userPasswd;
       try
       {
        Class.forName("org.gjt.mm.mysql.Driver");
        Connection connection=DriverManager.getConnection(url);
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql="SELECT * FROM "+tableName;
        ResultSet rs = statement.executeQuery(sql);

        while(true) 
           {
               if(rs.next())
               {
                    if((this.name.equals(rs.getString(1)))&&(this.pass.equals(rs.getString(2))))
                    {
                  
                       out.print("<h1>"+"Login Success!");
                  
                       break;
                     }
               }
               else
               {
                  out.print("<h1>"+"Account or Password Is Invalid!");
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
