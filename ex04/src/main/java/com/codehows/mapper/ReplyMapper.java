package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

public interface ReplyMapper {
	
	//create
	public int insert(ReplyVO vo);
	
	//read
	public ReplyVO read(Long rno);	//Ư�� ��� �б�
	
	//delete
	public int delete (Long rno);
	
	//update
	public int update(ReplyVO reply);
	
	//@Param ������̼��� �̿��� ��� ��� �����
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,				//criteria �ȿ� �ִ� cri�� �־��ְڴ�.
			@Param("bno") Long bno);

	public int getCountByBno(Long bno);
}
