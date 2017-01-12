package com.jay.servlet;

import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**servlet实际就是一个java文件*/  
public class MyServlet extends HttpServlet {  
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyServlet.class);
        
//    //调用父类的构造方法   
//    public MyServlet(){  
//        super();  
//    }  
  
    //重写父类的doGet()方法   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	
    	display("调用了doget");
    	logger.info("调用了doget");
        //通常情况是将get请求转发到post请求中去   
        doPost(request,response);  
      
    }  
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        // TODO Auto-generated method stub   
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
        
    	display("调用了doPost");
    	logger.info("调用了doPost");
    	//编写处理post请求的响应信息   
    	String name=request.getParameter("name");
    	String string ="";
        string = "I am coming !!!!! happy!"+name;
    	display(string);
    	logger.info(string);
    	
    	
        PrintWriter pw=response.getWriter();  
        pw.println(string);  
        
        
        pw.flush();  
        pw.close();  
    }  
      

    
    
 //复用输出函数
  private void display(Object o){
	  System.out.println(o);
  }
    
    
    
    
}  
