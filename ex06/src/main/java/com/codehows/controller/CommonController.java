package com.codehows.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	//Http GET ��û�� /accessError ��η� ���� �� �� �޼ҵ尡 ó���Ѵ�.
	//�� �޼ҵ�� �������� ���� ����ڰ� ���� ���ѵ� �������� �������Ϸ��� �� �� ȣ��ȴ�.
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
	//accessDenied �޼ҵ�� Authentication ��ü�� Model ��ü�� �Ű� ������ �޴´�.
	//Authentication ��ü�� ���� ������� ���� ������ ��Ÿ����, Model ��ü�� ��� ������ �����͸� ��µ� ����Ѵ�.
		
		log.info("access Denied : " + auth);
		
		//model.addAttribute()�� ����Ͽ� "msg"��� �Ӽ��� "Access Denied"��� �޽����� �߰��Ͽ� ��� �����Ѵ�.
		model.addAttribute("msg", "Access Denied");
	}
	
	//customLogin
	//Http GET ��û�� /customLogin ��η� ���� �� loginInput()�޼ҵ尡 ó���Ѵ�.
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
	//error, logout, model �Ű�����
	//error: �α��� �� �߻��� ���� �޽��� ���� �޴´�.
	//logout: �α׾ƿ� �������� ���� �޽����� ���� �޴´�.
	//model: �信 �����͸� �����ϱ� ���� spring�� �� ��ü
		
		//error�� logout �Ķ���� ���� �α׿� ���
		log.info("error: " + error);
		log.info("logout: " + logout);
		
		//error�� null�� �ƴ϶�� �α��� �� ������ �߻��� ��
		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		//logout�� null �ƴ϶�� �α׾ƿ� ����
		if(logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}
	
	//�α׾ƿ�
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}

}
