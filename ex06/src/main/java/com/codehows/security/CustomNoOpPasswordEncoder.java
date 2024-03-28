package com.codehows.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
// 이 클래스는 비밀번호를 인코딩하고 일치 여부를 확인하는 간단한 방법을 제공한다.
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	
	//encode()메소드는 주어진 원시 비밀번호(rawpassword)를 받아와 서 그대로 반환
	//비번을 변형하지 않고 그대로 반환, 이것이 No-op(No Operation)을 나타낸다.
	public String encode(CharSequence rawPassword) {
			
			log.warn("before encode :" +rawPassword);
			
			//메소드 안에 주어진 원시 비번을 받아와서 toString()메소드를 사용해서 문자열로 변환 후 반환한다.
			return rawPassword.toString();
		}
		
		//matches()메소드는 주어진 원시 비번과 인코딩된 비번(encodedpw)가 일치하는지 확인한다.		
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			
			//이 메소드는 주어진 원시 비번과 인코딩된 비번을 로그에 기록 후
			log.warn("matches: " + rawPassword + ":" + encodedPassword);
			
			//두 비번이 같은지 비교하여 일치하면 true 반환, 아니면 false 반환
			return rawPassword.toString().equals(encodedPassword);
		}
	}


