package com.jay.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

 
@Controller// 用来标注当前类是springmvc的控制层的类
//@RequestMapping("/test") // RequestMapping表示 该控制器的唯一标识或者命名空间。 放在方法上面用来指定某个方法的路径，当它放在类上时命名空间需要组合方法上的requestmapping来访问。
public class Hello {

   

        /*1.
         * 因为这个函数没有设定为返回体，所以这个函数的返回结果是到/WEB-INF/Views/中的界面，返回的是jsp文件的名字。不会把内容返回给前端。
         * 这里返回了字符串“hello”，那么程序控制下一步就会跳转到/WEB-INF/Views/目录下的“hello.jsp”，所以hello.jsp事先就要有，否则会找不到文件。
         * 如果这个方法返回的是“xxx”，那么下一步就会调用相应目录下的“xxx.jsp”文件。
         * 说白了，这个方法的作用就是选择跳转界面，是下一步的定位跳转。是一个定位函数，而不是返回内容。
         * */
        @RequestMapping(value="/hello")
        public String hello(){
            System.out.println("spring mvc hello world!");

            return "hello";
        }

       

        /*2.
         * 这个方法直接设定是ResponseBody,是返回体。所以会把结果内容直接返回到前端页面上。
         * 不会跳转到/WEB-INF/Views/目录下的“hello.jsp”
         * 返回值有乱码，则可以在requestMapping中添加参数解决produces = "application/json; charset=utf-8"
         * */
        @RequestMapping(value="/ok",produces = "application/json; charset=utf-8")
        @ResponseBody
        public String ok(){
        	
            System.out.println("ok");

            List<String> list=new ArrayList<String>(); 

            list.add("TV电视机"); 
            list.add("ball球"); 
            list.add("shangdong山东省"); 

            return list.toString(); 

        }

        
        /*3.
         * 这个方法直接设定是ResponseBody,是返回体。所以会把结果内容直接返回到前端页面上。
         * 不会跳转到/WEB-INF/Views/目录下的“xx.jsp”。
         * 这里增加了方法的请求方式为“GET”，增加了前端传来的参数并且成功提取出来。
         * produces参数解决返回值中文乱码，也可以在配置文件中设置编码
         * */
        @RequestMapping(value="/isOK",method = RequestMethod.GET)
        @ResponseBody
        public String isOK(HttpServletRequest request){
        	System.out.println("request编码="+request.getCharacterEncoding());
        	try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


        	
        	String valueString = null;
        	String str = null;
			try {
				//获取参数方法一
	        	 valueString = request.getParameter("v");
				
				//获取参数方法二
				str = URLDecoder.decode(request.getParameter("v"),"UTF-8");
			} catch (Exception e) {
				System.out.println("获取参数异常");
				return "para error";
//				e.printStackTrace();
			}
        	
        	
            System.out.println("isOK valueString="+valueString);
            System.out.println("isOK str="+str);

            List<String> list=new ArrayList<String>(); 

            list.add("TV电视机"); 
            list.add("ball球"); 
            list.add("shangdong山东省撒大丰收"); 

            return list.toString(); 

        }
         
        
        
        /*
         * 4.
         * 这个方法直接设定是ResponseBody,是返回体。所以会把结果内容直接返回到前端页面上。
         * 不会跳转到/WEB-INF/Views/目录下的“xx.jsp”。
         * 这里增加了方法的请求方式为“POST”，增加了前端传来的json格式的参数并且成功提取出来,最后返回处理结果
         * 
         * 如{“v”:12}
         * */
        @RequestMapping(value = "/postData",method = RequestMethod.POST)
        @ResponseBody 
        public String postData(@RequestBody String request){
        	System.out.println("request="+request);
        	
        	//前端传进的是json对象，把reques转为json对象再提取数据
        	 JSONObject jb=new JSONObject();
    	     Object valueString = jb.fromObject(request).get("v");

    	     System.out.println("isOK valueString="+valueString);
    	     

           return "This is post"+valueString;
        }

     


        
        
        
        

}