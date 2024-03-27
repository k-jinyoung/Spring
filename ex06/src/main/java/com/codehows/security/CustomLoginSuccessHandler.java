package com.codehows.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
//AuthenticationSuccessHandler 인터페이스는 사용자의 로그인 성공 시 처리해야할 로직을 정의한 인터페이스다
	
	
	//onAuthenticationSuccess()메소드는 사용자의 로그인 성공 시 호출되어 처리해야 할 로직을 구현한다.
	//HttpServletRequest : HTTP 요청을 나타내는 객체
	//HttpServletResponse : HTTP 응답을 나타내는 객체
	//Authentication : 사용자의 인증 정보를 나타내는 객체
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
		HttpServletResponse response, Authentication auth) throws IOException, ServletException{
			
			log.info("Login Success");
			
			//roleNames 라는 문자열 리스트를 생성한다.
			// 이 리스트에는 사용자가 가지고 있는 권한들이 저장이 된다.
			List<String> roleNames = new ArrayList<>();
			
			//auth.getAuthorities()를 통해 현재 로그인한 사용자의 권한 정보를 가져온다.
			//forEach 메소드를 사용하여 각 권한(authority)을 순회하면서 처리한다.
			// 각 권한을 문자열 리스트인 roleNames에 추가한다.
			//authority.getAuthority()를 호출하여 권한 객체(authority)에서 권한 이름을 가져온다.
			auth.getAuthorities().forEach(authority -> {
				roleNames.add(authority.getAuthority());		//이것이 실제 권한의 문자열 표현
			});
			
			log.warn("ROLE NAMES: " + roleNames);
			
			if(roleNames.contains("ROLE_ADMIN")) {
				response.sendRedirect("/sample/admin");
				return;
			}
			
			if(roleNames.contains("ROLE_MEMBER")) {
				response.sendRedirect("/sample/member");
				return;
			}
			
			response.sendRedirect("/");
		}

}
