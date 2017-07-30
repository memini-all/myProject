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
	public String userRegist(HttpServletRequest request, 
			@ModelAttribute("userVO") UserVO userVO, RedirectAttributes rttr) throws Exception {
    	
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
			
			Criteria cri = new Criteria();
			cri.setPerPageNum(20);	
			
			List<LoginVO> historyList = service.selectLoginHistoryList(cri, userVO.getUserno());
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
	public ResponseEntity<Map<String, Object>> userLoginHistory4Ajax(
			@PathVariable("userno") Integer userno, int start, int end) throws Exception{

		ResponseEntity<Map<String, Object>> entity = null;

		try {

			Criteria cri = new Criteria();
			cri.setPage(start);
			cri.setPerPageNum(end);	
			
			List<LoginVO> historyList = service.selectLoginHistoryList(cri, userno);	
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("historyList", historyList);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
	// 회원정보 보기
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(HttpServletRequest request, Model model) throws Exception{
		
		logger.info(">>>>>>> 회원정보 보기 .......");
		
		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			
			UserVO userVO = (UserVO)sessionObj;	
			UserVO userInfo = service.selectUserInfo(userVO.getUserno());
			
			model.addAttribute("userVO", userInfo);
			
			return "/user/UserInfo";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	

	}
	
	// 회원정보 수정 화면
	@RequestMapping(value = "/view/update", method = RequestMethod.GET)
	public String userUpdateView(HttpServletRequest request, Model model) throws Exception{
			
		logger.info(">>>>>>> 회원정보 수정 화면 .......");
			
		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
			
		if(sessionObj != null){
				
			UserVO userVO = (UserVO)sessionObj;	
			UserVO userInfo = service.selectUserInfo(userVO.getUserno());

			model.addAttribute("userVO", userInfo);
				
			return "/user/UserModify";
		}
			
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	
	
	// 회원정보 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String userUpdate(HttpServletRequest request, 
			@ModelAttribute("userVO") UserVO userVO, RedirectAttributes rttr) throws Exception{
			
		logger.info(">>>>>>> 회원정보 수정 .......");

		service.updateUser(userVO, request);
		
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/user/info";
	}
	
	
	// 삭제처리
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> userDelete(
			@ModelAttribute("userVO") UserVO userVO) throws Exception {

		logger.info(">>>>>>> 회원 탈퇴 ........");

		ResponseEntity<String> entity = null;
	
		try {
			
			int result = service.selectDeleteUserInfo(userVO);
	
			if(result == 0){
				entity = new ResponseEntity<String>("NOUSER", HttpStatus.OK);
			}
			else if(result == 1){
					
				service.deleteUser(userVO.getUserno());
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// 탈퇴작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}

	
	// 활동내역
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String userActivity(HttpServletRequest request, Model model) throws Exception{

		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			
			logger.info(">>>>>>> 활동 기록 .......");

			UserVO userVO = (UserVO)sessionObj;
			UserVO userInfo = service.selectUserInfo(userVO.getUserno());
			
			model.addAttribute("userVO", userInfo);
				
			return "/user/UserActivity";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	
	// 아이디 중복체크 
	@RequestMapping(value = "/checkid", method = RequestMethod.GET)
	public ResponseEntity<Integer> checkUserID(String userid) throws Exception{
		
		ResponseEntity<Integer> entity = null;

		try {
			logger.info(">>>>>>> 아이디 중복체크 ........");
			
			int result = service.selectDuplUserId(userid);	
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	// 사용자관리 화면
	@RequestMapping(value = "/mgt", method = RequestMethod.GET)
	public String userManagementView(HttpServletRequest request, Model model) throws Exception{
		
		logger.info(">>>>>>> 사용자 관리 화면 .......");
		
		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			
			UserVO userVO = (UserVO)sessionObj;	
			String authority = userVO.getAuthority();
			
			if(authority.equals("AD")){

				int userCount = service.selectUserCount("");
				model.addAttribute("userCount", userCount);
				
				return "/user/UserManagement";
			}else{
				return "redirect:/board/list";	// 로그인한 사용자가 관리자가 아닐경우
			}
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	
	// 사용자관리
	@RequestMapping(value = "/mgt/userlist", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> userManagement(String keyword) throws Exception{

		ResponseEntity<List<UserVO>> entity = null;

		try {

			Criteria cri = new Criteria();
			cri.setPerPageNum(3);	
	
			List<UserVO> userList = service.selectUserList(keyword, cri);
			entity = new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			// 작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	// 사용자관리 - 추가로드 클릭 시 사용자 목록 추가로 가져온다.
	@RequestMapping(value = "/mgt/addition", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> userListAddition(int start, int end, 
			int flag, String keyward) throws Exception{

		ResponseEntity<List<UserVO>> entity = null;

		try {

			Criteria cri = new Criteria();
			cri.setPage(start);
			cri.setPerPageNum(end);	
			
			List<UserVO> userList = service.selectUserAddition(keyward, cri, flag);
			entity = new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	// 탈퇴회원 보기
	@RequestMapping(value = "/mgt/withdraw", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> userWithdrawList() throws Exception{

		ResponseEntity<List<UserVO>> entity = null;

		try {

			Criteria cri = new Criteria();
			cri.setPerPageNum(3);
			
			List<UserVO> userList = service.selectWithdrawUser(cri);		
			entity = new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	// 잠금회원 보기
	@RequestMapping(value = "/mgt/lock", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> userLockList() throws Exception{

		ResponseEntity<List<UserVO>> entity = null;

		try {

			Criteria cri = new Criteria();
			cri.setPerPageNum(3);
			
			List<UserVO> userList = service.selectLockUser(cri);	
			entity = new ResponseEntity<List<UserVO>>(userList, HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 사용자 정보 수정
	@RequestMapping(value = "/mgt/update", method = RequestMethod.POST)
	public ResponseEntity<String> mgtUserUpdate(@RequestBody List<UserVO> userList) throws Exception{

		ResponseEntity<String> entity = null;
		
		try {
			
			service.updateMgtUser(userList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/**************************/
	
	// 아이디 찾기
	@RequestMapping(value = "/find/id", method = RequestMethod.GET)
	public ResponseEntity<String> findUserId(String username, String email) throws Exception{
		
		ResponseEntity<String> entity = null;

		try {
			logger.info(">>>>>>> 아이디 찾기 ........");
			
			String userid = service.selectUserId(username, email);	
			entity = new ResponseEntity<String>(userid, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 비밀번호 찾기
	@RequestMapping(value = "/find/pw", method = RequestMethod.GET)
	public ResponseEntity<String> findUserPw(String userid) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		try {
			logger.info(">>>>>>> 비밀번호 찾기 ........");
			
			String result = service.selectUserPw(userid);
			entity = new ResponseEntity<String>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/*************************/
	
	// @PathVariable를 사용하면 500번 에러가 발생함
	// @PathVariable는 @RequestMapping(value = "/all/{brdno}" 이렇게 있다면 여기서 {brdno} 값을 가져와 변수에 담아준다.
	@RequestMapping(value = "/checkid2")
    public void chkUserid(HttpServletRequest request, HttpServletResponse response, String userid) {
       
		logger.info(">>>>>>> 아이디 중복체크 ........"+userid);
    }
}
