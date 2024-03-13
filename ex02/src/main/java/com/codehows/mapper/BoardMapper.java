package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.codehows.domain.BoardVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);			//PK ���� �� �ʿ䰡 ���� ���
	
	public void insertSelectKey(BoardVO board);	// PK ���� �� �ʿ䰡 �ִ� ���

}
