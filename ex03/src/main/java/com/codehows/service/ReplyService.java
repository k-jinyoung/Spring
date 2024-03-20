package com.codehows.service;

import java.util.List;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyPageDTO;
import com.codehows.domain.ReplyVO;

public interface ReplyService {

	//mapper 안에 insert(생성)
	public int register(ReplyVO vo);
	
	//mapper 안에 read(조회)
	public ReplyVO get(Long rno);
	
	//mapper 안에 update(수정)
	public int modify(ReplyVO vo);
	
	//mapper 안에 delete(삭제)
	public int remove(Long rno);
	
	//mapper 안에 getListWithPaging
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
