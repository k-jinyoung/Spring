package com.codehows.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	//Http GET��û�� "/sample/all" ��η� ���� ��
	//doAll() �޼ҵ尡 ó���Ѵ�.
	@GetMapping("/all")
	public void doAll() {
		
		log.info("do all can access everybody");
	}
	
	//Http GET��û�� "/sample/member" ��η� ���� ��
	//doMember() �޼ҵ尡 ó���Ѵ�.
	@GetMapping("/member")
	public void doMember() {
		
		log.info("logined member");
	}
	
	//Http GET��û�� "/sample/admin" ��η� ���� ��
	//doAdmin() �޼ҵ尡 ó���Ѵ�.
	@GetMapping("/admin")
	public void doAdmin() {
		
		log.info("admin only");
	}

}
