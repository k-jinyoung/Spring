package com.codehows.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//이 클래스는 접근 거부(403 Forbidden) 상황에서 어떻게 처리할지를 정의한다.
	
	@Override
	//handle() 메소드는 접근 거부 상황에서 호출되어 처리해야 할 로직을 구현한다.
	//이 메소드는 HttpServletRequest와 HttpServletResponse, AccessDeniedException을 매개변수로 받는다.
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessException)
	throws IOException, ServletException{
		
		//메소드 내부에서는 로깅을 통해 두 메시지를 기록한다.
		log.error("Access Denied Handler");
		
		log.error("Redirect....");
		
		//접근이 거부된 경우에 사용자를 /accessError 페이지로 리다이렉트하여 접근 거부 상황을 보여준다.
		response.sendRedirect("/accessError");
	}

}
