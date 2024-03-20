package com.codehows.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect						//�ش� Ŭ������ ��ü�� Aspect�� ������ ������ ��Ÿ���� ���ؼ� ����Ѵ�.
@Log4j
@Component					//bean���� �ν��ϱ� ���ؼ� ����Ѵ�.
public class LogAdvice {
	
	@Before("execution(* com.codehows.service.SampleService*.*(..))")		//Aspectj�� ǥ�����̴�.
	public void logBefore() {
		
		log.info("==========================");
	}

}
