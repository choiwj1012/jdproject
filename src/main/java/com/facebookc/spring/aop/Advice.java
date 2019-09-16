package com.facebookc.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class Advice {

    private static final Logger logger = LoggerFactory.getLogger(Advice.class);

    // 공통으로 사용될 포인트컷을 지정합니다.
    // com.tisotry.pentode 패키지 안의 Controller 로 끝나는 클래스의 모든 메소드에 적용됩니다.
    @Pointcut("execution(* com.facebookc.spring.*Controller.*(..))")
    public void commonPointcut() { }

    // Before Advice 입니다. 위에서 정의한 공통 포인터 컷을 사용합니다.
    @Before("commonPointcut()")
    public void beforeMethod(JoinPoint jp) throws Exception {
        logger.info("beforeMethod() called.....");

        // 호출될 메소드의 인자들으 얻을 수 있습니다.
        Object arg[] = jp.getArgs();

        // 인자의 갯수 출력
        logger.info("args length : " + arg.length);

        // 첫 번재 인자의 클래스 명 출력
        logger.info("arg0 name : " + arg[0].getClass().getName());

        // 호출될 메소드 명 출력
        logger.info(jp.getSignature().getName());
        
    }


    // Around Advice 입니다.
    // 포인트컷을 직접 지정했습니다.
//    @Around("execution(* com.facebookc.spring.*Controller.*(..))")
//    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
//
//        logger.info("aroundMethod() before called.....");
//
//        Object result = pjp.proceed();
//        
//        logger.info("aroundMethod() after called.....");
//
//        return result;
//    }
    
    @Around("execution(* com.facebookc.spring.*Controller.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest request = null;
        HttpServletResponse response = null;
        
        for ( Object o : joinPoint.getArgs() ){ 
            if ( o instanceof HttpServletRequest ) {
                request = (HttpServletRequest)o;
            } 
            if ( o instanceof HttpServletResponse ) {
                response = (HttpServletResponse)o;
            } 
        }
        
        
        	logger.info("aroundMethod() before called.....");
        	
        	response.sendRedirect("www.naver.com");
        	
        	logger.info("aroundMethod() after called.....");
        	
        	Object result = joinPoint.proceed();
        	return result;

    }
    
    // After Returning Advice 입니다.
    // 이 어드바이스는 반환값을 받을 수 있습니다.
    @AfterReturning(pointcut="commonPointcut()", returning="returnString")
    public void afterReturningMethod(JoinPoint jp, String returnString) throws Exception {
    	
    	logger.info("afterReturningMethod() called.....");
    	// 호출된 메소드 반환값 출력
    	logger.info("afterReturningMethod() returnString : " + returnString);
    }

    // After Advice 입니다.
    @After("commonPointcut()")
    public void afterMethod(JoinPoint jp) throws Exception {
    	logger.info("afterMethod() called.....");
    }
    
    // 예외가 발생했을때 Advice 입니다.
    @AfterThrowing(pointcut="commonPointcut()", throwing="exception")
    public void afterThrowingMethod(JoinPoint jp, Exception exception) throws Exception {
        logger.info("afterThrowingMethod() called.....");

        // 발생한 예외의  메세지를 출력합니다.
        logger.info(exception.getMessage());
    }
} 
