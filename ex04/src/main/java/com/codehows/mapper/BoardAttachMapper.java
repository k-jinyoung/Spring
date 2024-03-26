package com.codehows.mapper;

import java.util.List;

import com.codehows.domain.BoardAttachVO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(Long bno);
	//�Խù��� ��ȣ�� �̿��ؼ� BoardAttachVO Ÿ������ ��ȯ�ϴ� �޼ҵ��� findByBno()�޼ҵ尡 �ϼ��� ����
	
	public void deleteAll(Long bno);

}
