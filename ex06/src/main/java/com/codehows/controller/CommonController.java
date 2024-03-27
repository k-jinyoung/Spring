package com.codehows.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	//Http GET 요청이 /accessError 경로로 들어올 때 이 메소드가 처리한다.
	//이 메소드는 인증되지 않은 사용자가 보안 제한된 페이지에 엑세스하려고 할 때 호출된다.
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
	//accessDenied 메소드는 Authentication 객체와 Model 객체를 매개 변수로 받는다.
	//Authentication 객체는 현재 사용자의 인증 정보를 나타내고, Model 객체는 뷰로 전달할 데이터를 담는데 사용한다.
		
		log.info("access Denied : " + auth);
		
		//model.addAttribute()를 사용하여 "msg"라는 속성에 "Access Denied"라는 메시지를 추가하여 뷰로 전달한다.
		model.addAttribute("msg", "Access Denied");
	}
	
	//customLogin
	//Http GET 요청이 /customLogin 경로로 들어올 때 loginInput()메소드가 처리한다.
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
	//error, logout, model 매개변수
	//error: 로그인 중 발생한 오류 메시지 전달 받는다.
	//logout: 로그아웃 성공했을 때의 메시지를 전달 받는다.
	//model: 뷰에 데이터를 전달하기 위한 spring의 모델 객체
		
		//error와 logout 파라미터 값을 로그에 기록
		log.info("error: " + error);
		log.info("logout: " + logout);
		
		//error가 null이 아니라면 로그인 중 오류가 발생한 것
		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}
		
		//logout이 null 아니라면 로그아웃 성공
		if(logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}
	
	//로그아웃
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}

}
