package com.codehows.service;

import java.util.List;

import com.codehows.domain.BoardVO;

public interface BoardService {
	
	public void register(BoardVO board);					//�� ���
	
	public BoardVO get(Long bno);							//bno �������� �� ��������
	
	public boolean modify(BoardVO board);					//BoardVO ��ü �ȿ� �������� �� ����	
	
	public boolean remove(Long bno);						//bno �������� �� ����
	
	public List<BoardVO> getList();							//��ü ���� ��������
}
