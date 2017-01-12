package com.login.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LogAroundAdvice implements MethodInterceptor{
	private Logger logger = Logger.getLogger(LogAroundAdvice.class);


	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long beginTime = System.currentTimeMillis();
		invocation.proceed();
		long endTime = System.currentTimeMillis();
		
		String targetMethodName = invocation.getMethod().getName();
		
		String logInfo = "环绕通知："+targetMethodName+"方法被调用，开始时间是"+beginTime+"耗时="+(endTime-beginTime)+"秒。";
		logger.info(logInfo);		
		return null;
	}


	
	
	
	
	
	
	
	
	
	
}
