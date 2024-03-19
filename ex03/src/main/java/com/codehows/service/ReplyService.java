package com.codehows.service;

import java.util.List;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

public interface ReplyService {

	//mapper �ȿ� insert(����)
	public int register(ReplyVO vo);
	
	//mapper �ȿ� read(��ȸ)
	public ReplyVO get(Long rno);
	
	//mapper �ȿ� update(����)
	public int modify(ReplyVO vo);
	
	//mapper �ȿ� delete(����)
	public int remove(Long rno);
	
	//mapper �ȿ� getListWithPaging
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
