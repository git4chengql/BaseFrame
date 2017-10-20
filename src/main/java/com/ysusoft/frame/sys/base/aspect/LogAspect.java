package com.ysusoft.frame.sys.base.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志统一处理
 * @author qlcheng
 * @date 2017年4月7日 上午10:53:16
 */
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* com.ysusoft..*Service.add*(..))")
	public void logPointCut(){}
	
	@Before("logPointCut()")
	public void beforeOp(JoinPoint joinPoint){
		System.out.println("操作开始前:");
	}
	
	@After("logPointCut()")
	public void afterOp(JoinPoint joinPoint){
		System.out.println("操作结束后:");
	}
}
