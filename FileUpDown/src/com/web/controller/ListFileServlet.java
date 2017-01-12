package com.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @ClassName: ListFileServlet
* @Description: 列出Web系统中所有下载文件
* @author: 孤傲苍狼
* @date: 2015-1-4 下午9:54:40
*
*/ 
public class ListFileServlet extends HttpServlet {
	private static final long serialVersionUID = 6018231797190334531L;
	
	private String serverPath = "/WEB-INF/upload";
	

    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //获取上传文件的目录
        String uploadFilePath = this.getServletContext().getRealPath(serverPath);
        
        Map<String,String> fileNameMap = new HashMap<String,String>();//获取服务器目录可以下载的文件名，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表一个目录
        
        //将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/listfile.jsp").forward(request, response);
    }
    
    /**
    * @Method: listfile
    * @Description: 递归遍历指定目录下的所有文件
    * @Anthor:孤傲苍狼
    * @param file 即代表一个文件，也代表一个文件目录
    * @param map 存储文件名的Map集合
    */ 
    private void listfile(File file,Map<String,String> map){
       
        if(!file.isFile()){ //如果file是一个目录
            File files[] = file.listFiles(); //列出该目录下的所有文件和目录
            
//            for(File f : files){ //遍历files[]数组
//                listfile(f,map);//递归
//            }
            for(int i =0; i < files.length;i++){ //遍历files[]数组
                listfile(files[i],map);//递归
            }
        }else{//是文件
            /**
             * 处理文件名，上传后的文件是以“uuid_文件名”的形式去重新命名的，去除文件名的uuid_部分
                file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
                                    那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            map.put(file.getName(), realName);//file.getName()得到的是文件在服务器的名称，这个名称是唯一的，因此可以作为key。realName是文件的名称（不含UUID），有可能会重复
        }
    }
    
    
    
    
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    
    
    
    
    
    
    
    
    
    
}
















