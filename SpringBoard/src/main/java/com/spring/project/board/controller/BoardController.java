package com.spring.project.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.core.util.StringUtils;
import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.service.BoardService;
import com.spring.project.common.util.PageCalculate;
import com.spring.project.common.util.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;

	// 게시물 보기
	// @ModelAttribute 사용으로 SearchCriteria가 BoardList.jsp로 자동으로 전달
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		logger.info(">>>>>>> 게시물 보기 ........");

		model.addAttribute("list", service.selectBoardList(cri));

		PageCalculate pCalculate = new PageCalculate();
		pCalculate.setCri(cri);
		pCalculate.setTotalCount(service.countPage(cri));

		model.addAttribute("pageCalculate", pCalculate);

		return "board/BoardList";
	}

	// 글 상세보기
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String boardRead(HttpServletResponse response, HttpServletRequest request, 
				@RequestParam("brdno") int brdno, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {
		
		// 저장된 쿠키 불러오기
		Cookie cookies[] = request.getCookies();
	
		Map<String, String> mapCookie = new HashMap<String, String>();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				mapCookie.put(cookie.getName(), cookie.getValue());
			}
		}

		// 저장된 쿠키중 viewcount만 찾는다.
		String viewCntCookie = mapCookie.get("viewcount");
		// 저장될 새로운 쿠키값 생성
		String newReadCount = "|" + brdno;

		logger.info(">>>>>>> viewCntCookie ........" + viewCntCookie);
		logger.info(">>>>>>> newReadCount  ........" + newReadCount);
		
		// 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사
		if (StringUtils.indexOfIgnoreCase(viewCntCookie, newReadCount) == -1) {
			// 없을 경우 쿠키 생성
			Cookie cookie = new Cookie("viewcount", viewCntCookie + newReadCount);
			cookie.setMaxAge(60); // 초단위
			response.addCookie(cookie);

			// 조회수 업데이트
			service.updateViewCnt(brdno);
		}
		
		Map<String, Object> resultMap = service.selectBoardDetail(brdno);
		
		logger.info(">>>>>>> 상세보기 ........");
		model.addAttribute("boardVO", resultMap.get("boardVO"));
		model.addAttribute("fileList", resultMap.get("fileList"));

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
	public String boardRegist(HttpServletRequest request, @ModelAttribute("board") BoardVO board, RedirectAttributes rttr) throws Exception {
    	
		logger.info(">>>>>>> 등록작업 .......");
		
		service.insertBoard(board, request);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

	// 수정화면
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(@RequestParam("brdno") int brdno, @ModelAttribute("cri") SearchCriteria cri, Model model)
			throws Exception {

		logger.info(">>>>>>> 수정 페이지 이동 .........");

		Map<String, Object> resultMap = service.selectBoardDetail(brdno);
		model.addAttribute("boardVO", resultMap.get("boardVO"));
		model.addAttribute("fileList", resultMap.get("fileList"));

		return "board/BoardModify";
	}

	// 수정처리
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String boardModify(HttpServletRequest request, BoardVO board, SearchCriteria cri, 
			RedirectAttributes rttr) throws Exception {
		
		logger.info(">>>>>>> 수정작업........");

		service.updateBoard(board, request);

		// 페이지 정보를 전달 - 수정 후 원래 페이지로 돌아오도록 처리
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

	// 삭제처리
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam("brdno") int brdno, @ModelAttribute("cri") SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {

		logger.info(">>>>>>> 삭제작업 ........");

		service.deleteBoard(brdno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

}
