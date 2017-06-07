package com.spring.project.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

	// 게시물 보기
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Model model) throws Exception {

		logger.info(">>>>>>> 모든 게시물 보기 ........");
		model.addAttribute("list", service.boardList());

		return "board/BoardList";
	}

	// 글 상세보기
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String boardRead(@RequestParam("brdno") int brdno, Model model) throws Exception {

		logger.info(">>>>>>> 상세보기 ........");
		model.addAttribute("boardVO", service.boardDetail(brdno));

		return "board/BoardDetail";
	}
	
	// 등록화면
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String boardForm(BoardVO board, Model model) throws Exception {
		logger.info(">>>>>>> 글작성 페이지 이동 .........");

		return "board/BoardForm";
	}

	// 글 등록
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String boardRegist(@ModelAttribute("board") BoardVO board, RedirectAttributes rttr) throws Exception {

		logger.info(">>>>>>> 등록작업 .......");

		logger.info("============> 제목 : " + board.getTitle());
		logger.info("============> 내용 : " + board.getContent());
		logger.info("============> 작성자 번호 : " + board.getUserno());

		service.boardRegist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}



	// 수정화면
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(int brdno, Model model) throws Exception {

		logger.info(">>>>>>> 수정 페이지 이동 .........");

		model.addAttribute("boardVO", service.boardDetail(brdno));

		return "board/BoardModify";
	}

	// 수정처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String boardModify(BoardVO board, RedirectAttributes rttr) throws Exception {

		logger.info(">>>>>>> 수정작업........");

		service.boardModify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}
	
	// 삭제처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam("brdno") int brdno, RedirectAttributes rttr) throws Exception {

		logger.info(">>>>>>> 삭제작업 ........");

		service.boardRemove(brdno);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

}
