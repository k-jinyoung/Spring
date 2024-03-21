package com.codehows.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService{
	
	//doAdd 오버라이딩(재정의)
		@Override
		public Integer doAdd(String str1, String str2) throws Exception {
			
			return Integer.parseInt(str1) + Integer.parseInt(str2);
			//parseInt로 str1과 str2 변환하여 합쳐서 반환하기
		}

}
