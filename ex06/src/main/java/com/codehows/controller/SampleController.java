package com.codehows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	//Http GET요청이 "/sample/all" 경로로 들어올 때
	//doAll() 메소드가 처리한다.
	@GetMapping("/all")
	public void doAll() {
		
		log.info("do all can access everybody");
	}
	
	//Http GET요청이 "/sample/member" 경로로 들어올 때
	//doMember() 메소드가 처리한다.
	@GetMapping("/member")
	public void doMember() {
		
		log.info("logined member");
	}
	
	//Http GET요청이 "/sample/admin" 경로로 들어올 때
	//doAdmin() 메소드가 처리한다.
	@GetMapping("/admin")
	public void doAdmin() {
		
		log.info("admin only");
	}

}
