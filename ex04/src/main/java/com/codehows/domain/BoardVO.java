package com.codehows.domain;

import java.util.Date;
import java.util.List;

import  lombok.Data;

@Data
public class BoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	
	private int replyCnt;
	
	//attachList라는 변수로 첨부파일의 정보를 수집한다.
	private List<BoardAttachVO> attachList;

}
