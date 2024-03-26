package com.codehows.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria (int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;		
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	public String getListLink() {
		//URI를 생성하기 위한 UriComponentsBuilder 객체를 생성한다.
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)		// pageNum 매개변수를 추가
			.queryParam("amount", this.getAmount())		// amount 매개변수를 추가
			.queryParam("type", this.getType())			// type 매개변수를 추가
			.queryParam("keyword", this.getKeyword());	// keyword 매개변수를 추가
		
		// 생성된 URI 문자열을 반환
		return builder.toUriString();
	}

}
