package com.codehows.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
		
		//����Ǵ� �ð��� start�� �־��ش�.
		long start = System.currentTimeMillis();
				
		log.info("Target: " + pjp.getTarget());						//������ �޼ҵ� pjp (�޼ҵ� ��ü)
		log.info("Param: " + Arrays.toString(pjp.getArgs()));		//Args�� �Ű� ���� (�޼ҵ� �Ű����� Ȯ��)
		
		//���Ͽ� ���� �ʱ�ȭ
		Object result = null;
		
		try {
			//���� ����
			result = pjp.proceed();		//�޼ҵ� ���� -> ��� ���� result�� �����Ѵ�.
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//�޼ҵ� ���� �� �ð� �� ��������
		long end = System.currentTimeMillis();				
		
		//�޼ҵ尡 �����ϴµ� �ɸ� �ð� ���ϱ�
		log.info("TIME: " + (end - start));					
		
		//�޼ҵ� ���� ��� ���� �����ؼ� ����Ѵ�.
		return result;
	}

}
