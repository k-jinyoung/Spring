package com.codehows.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect						//해당 클래스의 객체가 Aspect를 구현한 것임을 나타내기 위해서 사용한다.
@Log4j
@Component					//bean으로 인식하기 위해서 사용한다.
public class LogAdvice {
	
	@Before("execution(* com.codehows.service.SampleService*.*(..))")		//Aspectj의 표현식이다.
	public void logBefore() {
		
		log.info("==========================");
	}
	
	@Before("execution(* com.codehows.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	@AfterThrowing(pointcut = "execution(* com.codehows.service.SampleService*.*(..))", throwing= "exception")
	public void logException(Exception exception) {
		
		log.info("Exception....!!!!");
		log.info("Exception: " + exception);
	}
	
	@Around("execution(* com.codehows.service.SampleService*.*(..))")
	public Object logTime( ProceedingJoinPoint pjp) {
		
		//실행되는 시간을 start에 넣어준다.
		long start = System.currentTimeMillis();
				
		log.info("Target: " + pjp.getTarget());						//실행할 메소드 pjp (메소드 객체)
		log.info("Param: " + Arrays.toString(pjp.getArgs()));		//Args는 매개 변수 (메소드 매개변수 확인)
		
		//리턴에 대한 초기화
		Object result = null;
		
		try {
			//실행 시점
			result = pjp.proceed();		//메소드 실행 -> 결과 값을 result에 저장한다.
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//메소드 실행 후 시간 값 가져오기
		long end = System.currentTimeMillis();				
		
		//메소드가 실행하는데 걸린 시간 구하기
		log.info("TIME: " + (end - start));					
		
		//메소드 실행 결과 값을 리턴해서 출력한다.
		return result;
	}

}
