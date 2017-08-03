package com.spring.project.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.core.util.StringUtils;
import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.service.BoardService;
import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.PageCalculate;
import com.spring.project.common.util.SearchCriteria;


/**
 * 게시물 Controller <br>
 * /board/* URL을 처리한다.
 * 
 * @author adm
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;

	
	/**
	 * 게시물을 조회한다. @ModelAttribute 사용으로 SearchCriteria가 BoardList.jsp로 자동으로 전달
	 * 
	 * @param cri  {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * @param model {@link Model}
	 * 
	 * @return 글목록 화면 (board/BoardList)
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		//logger.info(">>>>>>> 게시물 보기 ........");
		
		model.addAttribute("noticeList", service.selectNoticeList(cri));
		model.addAttribute("list", service.selectBoardList(cri));

		PageCalculate pCalculate = new PageCalculate();
		pCalculate.setCri(cri);
		pCalculate.setTotalCount(service.countPage(cri));

		model.addAttribute("pageCalculate", pCalculate);

		return "board/BoardList";
	}



	/**
	 * 게시물 상세보기<br>
	 * 상세보기에서 다시 원래의 페이지로 돌아가기위해 SearchCriteria를 가져온다.
	 * 
	 * @param response HttpServletResponse
	 * @param request HttpServletRequest
	 * @param brdno 상세보기하는 글의 글번호
	 * @param cri  {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * @param model {@link Model}
	 * 
	 * @return 상세보기 화면 (board/BoardDetail)
	 * @throws Exception
	 */
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
		
		//logger.info(">>>>>>> 상세보기 ........");
		
		model.addAttribute("boardVO", resultMap.get("boardVO"));
		model.addAttribute("fileList", resultMap.get("fileList"));

		return "board/BoardDetail";
	}


	
	/**
	 * 글 작성 화면을 보여준다.
	 * 로그인 하지 않고 해당 경로로 접근하면 로그인 화면을 보여준다.
	 * 
	 * @param request HttpServletRequest
	 * @param model {@link Model}
	 * 
	 * @return 글작성 화면(board/BoardForm) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String boardForm(HttpServletRequest request, Model model) throws Exception {
		//logger.info(">>>>>>> 글작성 페이지 이동 .........");

		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			return "board/BoardForm";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}


	
	/**
	 * 글 등록 작업을 처리한다. 작성 후 글 목록화면으로 돌아간다.
	 * 
	 * @param request HttpServletRequest
	 * @param board {@link BoardVO} 글 작성 정보를 담고있는 VO 
	 * @param rttr {@link RedirectAttributes}
	 * 
	 * @return 글 목록 화면 (redirect:/board/list)
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String boardRegist(HttpServletRequest request, @ModelAttribute("board") BoardVO board, RedirectAttributes rttr) throws Exception {
    	
		//logger.info(">>>>>>> 등록작업 .......");

		service.insertBoard(board, request);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}


	
	/**
	 * 수정 화면을 보여준다. 
	 * 로그인 하지 않고 해당 경로로 접근하면 로그인 화면을 보여준다.<br>
	 * 수정 작업 후 다시 원래의 페이지로 돌아가기위해 SearchCriteria를 가져온다.
	 * 
	 * @param request HttpServletRequest
	 * @param brdno 수정할 글의 글번호
	 * @param cri  {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * @param model Model
	 * 
	 * @return 글 수정 화면(board/BoardModify) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modifyForm(HttpServletRequest request, @RequestParam("brdno") int brdno, 
			@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			//logger.info(">>>>>>> 수정 페이지 이동 .........");

			Map<String, Object> resultMap = service.selectBoardDetail(brdno);
			model.addAttribute("boardVO", resultMap.get("boardVO"));
			model.addAttribute("fileList", resultMap.get("fileList"));

			return "board/BoardModify";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}

	
	
	/**
	 * 글 수정 작업을 처리한다.
	 * 
	 * @param request HttpServletRequest
	 * @param board {@link BoardVO} 수정할 글 정보를 담은 VO
	 * @param cri {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * @param rttr RedirectAttributes
	 * 
	 * @return 글 목록 화면 (redirect:/board/list)
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String boardModify(HttpServletRequest request, BoardVO board, SearchCriteria cri, 
			RedirectAttributes rttr) throws Exception {
		
		//logger.info(">>>>>>> 수정작업........");

		service.updateBoard(board, request);

		// 페이지 정보를 전달 - 수정 후 원래 페이지로 돌아오도록 처리
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}


	
	/**
	 * 글 삭제 처리를 한다.
	 * 삭제 작업 후 다시 원래의 페이지로 돌아가기위해 SearchCriteria를 가져온다.
	 * 
	 * @param brdno 삭제할 글의 글번호
	 * @param cri {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * @param rttr RedirectAttributes
	 * 
	 * @return 글 목록 화면(redirect:/board/list)
	 * @throws Exception
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String boardRemove(@RequestParam("brdno") int brdno, @ModelAttribute("cri") SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {

		//logger.info(">>>>>>> 삭제작업 ........");

		service.deleteBoard(brdno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

	

	/**
	 * 내가 작성한 글 목록을 보여준다.
	 * 
	 * @param userno 사용자 번호
	 * @param page 작성한 글 페이지 정보
	 * 
	 * @return  {@link ResponseEntity} 작성글 목록과 페이지 정보
	 */
	@RequestMapping(value = "/user/{userno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> userArticleList4Ajax(
			@PathVariable("userno") Integer userno, @PathVariable("page") Integer page) {

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			//logger.info(">>>>>>> 작성글 조회 ........");
			
			int articleCount = service.selectUserArticleCnt(userno); // 작성한 글의 총 개수
			
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageCalculate pCalculate = new PageCalculate();
			pCalculate.setCri(cri);
			pCalculate.setTotalCount(articleCount);

			List<BoardVO> articleList = service.selectUserArticleList(cri, userno);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("articleList", articleList); 	// 글은 list로
			map.put("pageCalculate", pCalculate); 	// 페이지 관련 정보는 PageCalculate 객체로 전달
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 조회작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	


	/**
	 * 내가 작성한 글을 삭제한다.
	 * 
	 * @param arrayParams 삭제할 글번호 리스트
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ResponseEntity<String> userArticleDelete4Ajax(
			@RequestParam(value="delBrdnoArr[]") List<Integer> arrayParams) {

		ResponseEntity<String> entity = null;
		
		try {
			//logger.info(">>>>>>> 내가 쓴 글 삭제 ........"+arrayParams);
			
			service.deleteUserArticle(arrayParams);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 삭제작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
