package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.codehows.domain.BoardVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);			//PK 값을 알 필요가 없는 경우
	
	public void insertSelectKey(BoardVO board);	// PK 값을 알 필요가 있는 경우

}
