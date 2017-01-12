package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.helloworld.HelloWorld;
import com.login.biz.UserBiz;

public class TestLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//加载spring配置
		UserBiz userBiz = (UserBiz) context.getBean("userBiz");//获取配置中的实例
		userBiz.checkLogin("kb", "123");
		

	}

}
