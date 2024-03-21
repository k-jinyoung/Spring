package com.codehows.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import com.codehows.domain.ReplyVO;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	//테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = {280L, 279L, 278L, 277L, 276L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
		
	
	/*
	 * 생성 테스트
	 * @Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			//게시물의 번호
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);					
		});
	}*/
	
	/*//조회 테스트
	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}*/
	
	/*
	//delete test
	@Test
	public void testDelete() {
		Long targetRno = 1L;
		mapper.delete(targetRno);
	}*/
	
	/*//update
	@Test
	public void testUpdate() {
		Long targetRno = 10L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("Update Reply");
		int count = mapper.update(vo);
		log.info("UPDATE COUNT: " + count);
	}*/
	
	//@Param 어노테이션을 이용한 댓글 목록 만들기
	/*@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		//280L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}*/
	
	//페이징 처리 테스트
	@Test
	   public void testList2() {
	      Criteria cri = new Criteria(2, 10);
	      
	      List<ReplyVO> replies = mapper.getListWithPaging(cri, 280L);
	      replies.forEach(reply -> log.info(reply));
	   }
	
	
	/*
	 * @Test 
	 * public void testMapper() 
	 * { log.info(mapper); }
	 */
	

}
