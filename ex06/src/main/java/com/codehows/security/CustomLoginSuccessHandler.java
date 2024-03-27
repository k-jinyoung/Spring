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
//AuthenticationSuccessHandler �������̽��� ������� �α��� ���� �� ó���ؾ��� ������ ������ �������̽���
	
	
	//onAuthenticationSuccess()�޼ҵ�� ������� �α��� ���� �� ȣ��Ǿ� ó���ؾ� �� ������ �����Ѵ�.
	//HttpServletRequest : HTTP ��û�� ��Ÿ���� ��ü
	//HttpServletResponse : HTTP ������ ��Ÿ���� ��ü
	//Authentication : ������� ���� ������ ��Ÿ���� ��ü
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
		HttpServletResponse response, Authentication auth) throws IOException, ServletException{
			
			log.info("Login Success");
			
			//roleNames ��� ���ڿ� ����Ʈ�� �����Ѵ�.
			// �� ����Ʈ���� ����ڰ� ������ �ִ� ���ѵ��� ������ �ȴ�.
			List<String> roleNames = new ArrayList<>();
			
			//auth.getAuthorities()�� ���� ���� �α����� ������� ���� ������ �����´�.
			//forEach �޼ҵ带 ����Ͽ� �� ����(authority)�� ��ȸ�ϸ鼭 ó���Ѵ�.
			// �� ������ ���ڿ� ����Ʈ�� roleNames�� �߰��Ѵ�.
			//authority.getAuthority()�� ȣ���Ͽ� ���� ��ü(authority)���� ���� �̸��� �����´�.
			auth.getAuthorities().forEach(authority -> {
				roleNames.add(authority.getAuthority());		//�̰��� ���� ������ ���ڿ� ǥ��
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
