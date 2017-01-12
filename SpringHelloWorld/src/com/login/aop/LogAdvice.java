package com.login.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class LogAdvice implements MethodBeforeAdvice{
	private Logger logger = Logger.getLogger(LogAdvice.class);


    /*
     * 传入参数依次是：执行的方法，传入方法的参数，方法所在的类
     * 
     * */
	@Override
	public void before(Method method, Object[] arg, Object targetClass)
			throws Throwable {
		 String targetClassName = targetClass.getClass().getName();
		 String targetMethodName = method.getName();
		 
		 String logInfo = "前置通知："+targetClassName+"类的"+targetMethodName+"方法开始执行。";
		 int i = 0;
		 for (Object object : arg) {
			logInfo += "第"+ ++i +"个参数是"+object+";";
		}

		 logInfo = logInfo.substring(0, logInfo.length()-1);
		 logInfo +="。";
		 logger.info(logInfo);
		 
		 String errorInfo = null;
		 try {
			throw new Exception("前置通知的error info");
		} catch (Exception e) {
			errorInfo = e.getMessage();
		}
        logger.error(errorInfo);
		
	}

}
