package com.spring.project.user.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.project.common.util.Criteria;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.reply.dto.ReplyVO;
import com.spring.project.user.dto.UserVO;
import com.spring.project.user.service.UserService;


@Controller
@RequestMapping("/user/*")
public class UserController {

	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// 회원가입 화면
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userRegistView(Model model){
		logger.info(">>>>>>> 회원가입 화면 .......");
		return "/user/UserRegister";
	}
	
	// 회원가입 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegist(HttpServletRequest request, @ModelAttribute("userVO") UserVO userVO, RedirectAttributes rttr) throws Exception {
    	
		logger.info(">>>>>>> 회원가입 .......");

		service.insertUser(userVO, request);
		
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}
	
	
	// 로그인 기록
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String userLoginHistory(HttpServletRequest request, Model model) throws Exception{

		HttpSession session = request.getSession();
		
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			
			UserVO userVO = (UserVO)sessionObj;
			
			logger.info(">>>>>>> 로그인 기록 .......");
			logger.info(">>>>>>> 로그인 기록 ......."+userVO.getUserid() + ", no : "+userVO.getUserno());
			
			List<LoginVO> historyList = service.selectLoginHistoryList(new Criteria(), userVO.getUserno());
			int loginCount = service.selectLoginCount(userVO.getUserno());
			
			model.addAttribute("historyList", historyList);
			model.addAttribute("loginCount", loginCount);
			
			return "/user/UserHistory";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	
	// 로그인 기록 - 더보기 클릭시
	@RequestMapping(value = "/history/{userno}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> userLoginHistoryAjax(@PathVariable("userno") Integer userno, int start, int end) throws Exception{

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			
			logger.info(">>>>>>> 로그인기록 Ajax ........"+userno);
			
			logger.info(">>>>>>> 로그인기록 시작 ........"+start);
			logger.info(">>>>>>> 로그인기록 끝 ........"+end);

			Criteria cri = new Criteria();
			cri.setPage(start);
			cri.setPerPageNum(end);
			
			//List<LoginVO> historyList = service.selectLoginHistoryList(userno);		
			
			List<LoginVO> historyList = service.selectLoginHistoryList(cri, userno);	
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("historyList", historyList);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			// 등록작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
	
	// 아이디 중복체크 
	@RequestMapping(value = "/checkid", method = RequestMethod.GET)
	public ResponseEntity<Integer> checkUserID(String userid) throws Exception{
		
		ResponseEntity<Integer> entity = null;

		try {
			logger.info(">>>>>>> 아이디 중복체크 ........");
			
			int result = service.selectUserId(userid);	
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// @PathVariable를 사용하면 500번 에러가 발생함
	// @PathVariable는 @RequestMapping(value = "/all/{brdno}" 이렇게 있다면 여기서 {brdno} 값을 가져와 변수에 담아준다.
	@RequestMapping(value = "/checkid2")
    public void chkUserid(HttpServletRequest request, HttpServletResponse response, String userid) {
       
		logger.info(">>>>>>> 아이디 중복체크 ........"+userid);
    }
}
