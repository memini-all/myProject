package com.spring.project.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.project.board.dto.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// 등록 페이지를 보여줌
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String boardForm(BoardVO board, Model model) throws Exception {
		logger.info(">>>>>>> 등록 페이지 로딩.........");
		
		return "board/BoardForm";
	}
	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList() throws Exception {

		return "board/BoardList2";
	}
}
