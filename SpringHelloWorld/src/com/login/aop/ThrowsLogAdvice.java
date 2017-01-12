package com.login.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

public class ThrowsLogAdvice implements ThrowsAdvice{
	private Logger logger = Logger.getLogger(ThrowsLogAdvice.class);


    /*
     * 传入参数依次是：返回值，执行的方法，传入方法的参数，方法所在的类
     * 
     * */
	public void afterThrowing(Method method, Object[] args, Object targetClass,Throwable exceptionClass) {
		 String targetClassName = targetClass.getClass().getName();
		 String targetMethodName = method.getName();
		 
		 String logInfo = "异常通知："+targetClassName+"类的"+targetMethodName+"方法遇到异常。";

		 logger.info(logInfo);
		 
	}


}
