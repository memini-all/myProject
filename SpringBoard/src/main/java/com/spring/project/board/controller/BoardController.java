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
import com.spring.project.board.dto.Criteria;
import com.spring.project.board.dto.PageCalculate;
import com.spring.project.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

	// 게시물 보기
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(Criteria cri, Model model) throws Exception {

		logger.info(">>>>>>> 게시물 보기 ........");
		model.addAttribute("list", service.boardList(cri));

		PageCalculate pCalculate = new PageCalculate();
		pCalculate.setCri(cri);
		pCalculate.setTotalCount(service.listCount(cri));
		
		model.addAttribute("pageCalculate", pCalculate);
		
		return "board/BoardList";
	}

	// 글 상세보기
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String boardRead(@RequestParam("brdno") int brdno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

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

		service.boardRegist(board);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}



	// 수정화면
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(@RequestParam("brdno") int brdno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

		logger.info(">>>>>>> 수정 페이지 이동 .........");

		model.addAttribute("boardVO", service.boardDetail(brdno));

		return "board/BoardModify";
	}

	// 수정처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String boardModify(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {

		logger.info(">>>>>>> 수정작업........");

		service.boardModify(board);
		
		// 페이지 정보를 전달 - 수정 후 원래 페이지로 돌아오도록 처리
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
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
