package com.login.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;;

public class AllLogAdvice {
	private Logger logger = Logger.getLogger(AllLogAdvice.class);
	
	//前置通知
	public void myBeforeAdvice(JoinPoint joinpoint){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "前置通知："+className+"类的"+methodName+"方法开始执行。";
		
		logger.info(logInfo);
	}
	
	
	
	//后置通知
	public void myAfterReturnAdvice(JoinPoint joinpoint){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "后置通知："+className+"类的"+methodName+"方法已经执行结束。";
		
		logger.info(logInfo);
	}
	
	//异常通知
	public void myThrowingAdvice(JoinPoint joinpoint,Exception e){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "异常通知："+className+"类的"+methodName+"执行时遇到异常。";
		
		logger.info(logInfo);
	}
	
	//环绕通知
	public void myAroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable{
		long beginTime = System.currentTimeMillis();
        joinpoint.proceed();
		long endTime = System.currentTimeMillis();		
		
		String targetMethodName = joinpoint.getSignature().getName();

		String logInfo = "环绕通知："+targetMethodName+"方法被调用，开始时间是"+beginTime+"，耗时="+(endTime-beginTime)+"秒。";
		logger.info(logInfo);		
	}
	
	
	
}
