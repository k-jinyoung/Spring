package com.codehows.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codehows.domain.BoardVO;
import com.codehows.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	//서비스 구동 여부 확인
	/*@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}*/
	
	
	//새로운 게시물 추가
	/*@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}*/
	
	
	//전체 게시물 출력
	/*@Test
	public void testGetList() {
		
		service.getList().forEach(board -> log.info(board));
	}*/
	
	
	//특정 게시물 출력
	/*@Test
	public void testGet() {
		
		log.info(service.get(10L));
	}*/
	
	
	// 특정 게시물 삭제
	/*@Test
	public void testDelete() {
		
		//게시물 번호의 존재 여부를 확인하고 테스트 할 것
		log.info("REMOVE RESULT: " + service.remove(9L));
	}*/
	
	
	//특정 게시물 수정
	/*@Test
	public void testUpdate() {
		
		BoardVO board = service.get(11L);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("제목 수정합니다.");
		log.info("MODIFY RESULT: " + service.modify(board));
	}*/
	
	@Test
	public void testGetList() {
		
		//service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
	}

}
