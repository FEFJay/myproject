package com.login.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//定义日志切面
@Aspect
public class AllLogAdviceByAnnotation {
	private Logger logger = Logger.getLogger(AllLogAdviceByAnnotation.class);
	
	
	/*
	 * Pointcut用来定义切入点，切入点的名称定义为allMethod()
	 * 配置日志切入点 execution(* com.login.biz.UserBiz.*(..)), 是对UserBiz中的所有方法都进行拦截
	 * */
	@Pointcut("execution(* com.login.biz.UserBiz.*(..))")
	private void allMethod(){}
	
	
	
	//前置通知
	@Before("allMethod()")
	public void myBeforeAdvice(JoinPoint joinpoint){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "前置通知："+className+"类的"+methodName+"方法开始执行。";
		
		logger.info(logInfo);
	}
	
	
	
	//后置通知
	@AfterReturning("allMethod()")
	public void myAfterReturnAdvice(JoinPoint joinpoint){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "后置通知："+className+"类的"+methodName+"方法已经执行结束。";
		
		logger.info(logInfo);
	}
	
	//异常通知
	@AfterThrowing(pointcut="allMethod()", throwing="e")
	public void myThrowingAdvice(JoinPoint joinpoint,Exception e){
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		
		String logInfo = "异常通知："+className+"类的"+methodName+"执行时遇到异常。";
		
		logger.info(logInfo);
	}
	
	//环绕通知
	@Around("allMethod()")
	public void myAroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable{
		long beginTime = System.currentTimeMillis();
        joinpoint.proceed();
		long endTime = System.currentTimeMillis();		
		
		String targetMethodName = joinpoint.getSignature().getName();

		String logInfo = "环绕通知："+targetMethodName+"方法被调用，开始时间是"+beginTime+"，耗时="+(endTime-beginTime)+"秒。";
		logger.info(logInfo);		
	}
	
	
	
}
