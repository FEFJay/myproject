package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.helloworld.HelloWorld;

public class TestHelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");//加载spring配置
		HelloWorld helloWorld = (HelloWorld) context.getBean("myHelloWorld");//获取配置中的实例
		helloWorld.show();
		

	}

}
