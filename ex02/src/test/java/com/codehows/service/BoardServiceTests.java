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
	
	//���� ���� ���� Ȯ��
	/*@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}*/
	
	
	//���ο� �Խù� �߰�
	/*@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("���� �ۼ��ϴ� ��");
		board.setContent("���� �ۼ��ϴ� ����");
		board.setWriter("newbie");
		
		service.register(board);
		
		log.info("������ �Խù��� ��ȣ : " + board.getBno());
	}*/
	
	
	//��ü �Խù� ���
	/*@Test
	public void testGetList() {
		
		service.getList().forEach(board -> log.info(board));
	}*/
	
	
	//Ư�� �Խù� ���
	/*@Test
	public void testGet() {
		
		log.info(service.get(10L));
	}*/
	
	
	// Ư�� �Խù� ����
	/*@Test
	public void testDelete() {
		
		//�Խù� ��ȣ�� ���� ���θ� Ȯ���ϰ� �׽�Ʈ �� ��
		log.info("REMOVE RESULT: " + service.remove(9L));
	}*/
	
	
	//Ư�� �Խù� ����
	/*@Test
	public void testUpdate() {
		
		BoardVO board = service.get(11L);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("���� �����մϴ�.");
		log.info("MODIFY RESULT: " + service.modify(board));
	}*/
	
	@Test
	public void testGetList() {
		
		//service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
	}

}
