package com.codehows.mapper;

import java.util.List;

import com.codehows.domain.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(Long bno);
	//게시물의 번호를 이용해서 BoardAttachVO 타입으로 변환하는 메소드인 findByBno()메소드가 완성된 상태
	
	public void deleteAll(Long bno);

}
