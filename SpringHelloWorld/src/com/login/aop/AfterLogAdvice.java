package com.login.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class AfterLogAdvice implements AfterReturningAdvice{
	private Logger logger = Logger.getLogger(AfterLogAdvice.class);


    /*
     * 传入参数依次是：返回值，执行的方法，传入方法的参数，方法所在的类
     * 
     * */
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object targetClass) throws Throwable {
		 String targetClassName = targetClass.getClass().getName();
		 String targetMethodName = method.getName();
		 
		 String logInfo = "后置通知："+targetClassName+"类的"+targetMethodName+"方法已经执行结束，返回值是"+returnValue+"。";
		 int i = 0;
		 for (Object object : args) {
			logInfo += "传入方法的第"+ ++i +"个参数是"+object+";";
		}

		 logInfo = logInfo.substring(0, logInfo.length()-1);
		 logInfo +="。";
		 logger.info(logInfo);
		 
		 String errorInfo = null;
		 try {
			throw new Exception("后置通知里的error info");
		} catch (Exception e) {
			errorInfo = e.getMessage();
		}
        logger.error(errorInfo);
	}


}
