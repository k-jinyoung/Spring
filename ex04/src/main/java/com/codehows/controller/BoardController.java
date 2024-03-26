package com.codehows.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codehows.domain.BoardAttachVO;
import com.codehows.domain.BoardVO;
import com.codehows.domain.Criteria;
import com.codehows.domain.PageDTO;
import com.codehows.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	/*@GetMapping("/list")
	public void list(Model model) {
		
		log.info("list");
		model.addAttribute("list" , service.getList());
	}*/
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri,123));
		
		int total = service.getTotal(cri);
		
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri,total));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("============================");
		
		log.info("register: " + board);
		
		if (board.getAttachList() != null) {
			board.getAttachList().forEach(attach -> log.info(attach));
		}
		
		log.info("============================");
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		//로그에 삭제할 게시글 번호 출력
		log.info("remove..." + bno);
		
		//게시글에 첨부된 파일 목록 가져온다.
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		//만약 게시글을 삭제하고 성공했을 경우에
		if (service.remove(bno)) {
			//첨부 파일들을 삭제한다.
			deleteFiles(attachList);
			
			//삭제 성공 메시지를 리다이렉트 속성에 추가한다.
			rttr.addFlashAttribute("result", "success");
		}
		//삭제 후 목록 페이지로 리다이렉트하며, Criteria 객체가 가지고 있는 뭐리 매개변수를 함께 전달한다.
		return "redirect:/board/list" + cri.getListLink();
	}
	
	//getAttachList
	@GetMapping(value="/getAttachList", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		log.info("getAttachList" + bno);
		
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
		//ResponseEntity가 JSON 형태로 반환한다.
	}
	
	//파일 삭제 기능
	private void deleteFiles(List<BoardAttachVO> attachList) {
		//첨부파일 목록이 없거나 비어있는 경우 메소드를 종료
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		//로그에 파일 삭제 메시지 출력
		log.info("delete attach files..............");
		//로그에 첨부파일 목록 출력
		log.info(attachList);
		
		//첨부 파일 목록을 반복하면서 파일을 삭제한다.
		attachList.forEach(attach -> {
			try {
				//삭제할 파일의 경로를 생성한다.
				Path file = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\" +attach.getUuid()+"_"+attach.getFileName());
				
				//파일이 존재하면 삭제한다(실제 파일 삭제)
				Files.deleteIfExists(file);
				
				//만약 삭제된 파일이 이미지 파일인 경우 섬네일도 삭제한다.
				if(Files.probeContentType(file).startsWith("image")) {
					//섬네일 파일의 경로 생성한다.
					Path thumNail = Paths.get("C:\\upload\\"+attach.getUploadPath()+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					
					// 섬네일 파일 삭제
					Files.delete(thumNail);
				}
			} catch(Exception e) {
				//파일 삭제 중에 발생한 예외를 로그에 출력한다.
				log.error("delete file error" + e.getMessage());
			}	//end catch
		});	//end foreach
	}

}
