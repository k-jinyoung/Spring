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
	
	//�׽�Ʈ ���� �ش� ��ȣ�� �Խù��� �����ϴ��� �ݵ�� Ȯ���� ��
	private Long[] bnoArr = {280L, 279L, 278L, 277L, 276L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
		
	
	/*
	 * ���� �׽�Ʈ
	 * @Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			//�Խù��� ��ȣ
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("��� �׽�Ʈ" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);					
		});
	}*/
	
	/*//��ȸ �׽�Ʈ
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
	
	//@Param ������̼��� �̿��� ��� ��� �����
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		//280L
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	
	/*
	 * @Test 
	 * public void testMapper() 
	 * { log.info(mapper); }
	 */
	

}
