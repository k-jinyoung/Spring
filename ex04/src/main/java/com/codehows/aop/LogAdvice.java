package com.codehows.aop;

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

}
