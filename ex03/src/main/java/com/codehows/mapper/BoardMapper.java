package com.codehows.mapper;

import java.util.List;

import com.codehows.domain.BoardVO;
import com.codehows.domain.Criteria;

import org.apache.ibatis.annotations.Select;
import com.codehows.domain.BoardVO;

public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	
	public void insert(BoardVO board);			//PK ���� �� �ʿ䰡 ���� ���
	
	public Integer insertSelectKey(BoardVO board);	// PK ���� �� �ʿ䰡 �ִ� ���
	
	public BoardVO read(Long bno);
	
	public int delete (Long bno);
	
	public int update (BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
	

}
