package com.spring.project.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;
	
	
	// 등록 페이지를 보여줌
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String boardForm(BoardVO board, Model model) throws Exception {
		logger.info(">>>>>>> 글작성 페이지 이동 .........");

		return "board/BoardForm";
	}
	
	// 글 등록
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String boardRegist(@ModelAttribute("board") BoardVO board, RedirectAttributes rttr) throws Exception{
		
		logger.info(">>>>>>> 등록작업 .......");
		logger.info(">>>>>>> board : " + board.toString());

		logger.info("============> 제목 : " + board.getTitle());
		logger.info("============> 내용 : " + board.getContent());
		logger.info("============> 작성자 번호 : " + board.getUserno());	
		
		service.boardRegist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		//return "redirect:/board/BoardList2";
		return "redirect:/index";
	}
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList() throws Exception {
		logger.info(">>>>>>> 게시판 목록 페이지 이동 .........");
		
		return "board/BoardList2";
	}
}
