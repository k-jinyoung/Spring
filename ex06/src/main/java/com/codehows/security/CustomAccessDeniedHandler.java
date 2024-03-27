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
//�� Ŭ������ ���� �ź�(403 Forbidden) ��Ȳ���� ��� ó�������� �����Ѵ�.
	
	@Override
	//handle() �޼ҵ�� ���� �ź� ��Ȳ���� ȣ��Ǿ� ó���ؾ� �� ������ �����Ѵ�.
	//�� �޼ҵ�� HttpServletRequest�� HttpServletResponse, AccessDeniedException�� �Ű������� �޴´�.
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessException)
	throws IOException, ServletException{
		
		//�޼ҵ� ���ο����� �α��� ���� �� �޽����� ����Ѵ�.
		log.error("Access Denied Handler");
		
		log.error("Redirect....");
		
		//������ �źε� ��쿡 ����ڸ� /accessError �������� �����̷�Ʈ�Ͽ� ���� �ź� ��Ȳ�� �����ش�.
		response.sendRedirect("/accessError");
	}

}
