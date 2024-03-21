package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

public interface ReplyMapper {
	
	//create
	public int insert(ReplyVO vo);
	
	//read
	public ReplyVO read(Long rno);	//특정 댓글 읽기
	
	//delete
	public int delete (Long rno);
	
	//update
	public int update(ReplyVO reply);
	
	//@Param 어노테이션을 이용한 댓글 목록 만들기
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,				//criteria 안에 있는 cri를 넣어주겠다.
			@Param("bno") Long bno);

	public int getCountByBno(Long bno);
}
