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


/**
 * 사용자 Controller <br>
 * /user/* URL을 처리한다.
 * 
 * @author adm
 */
@Controller
@RequestMapping("/user/*")
public class UserController {

	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
 
	
	/**
	 * 회원가입 화면을 보여준다.
	 * 
	 * @param model {@link Model}
	 * 
	 * @return 회원가입 화면(/user/UserRegister)
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String userRegistView(Model model){
		//logger.info(">>>>>>> 회원가입 화면 .......");
		return "/user/UserRegister";
	}
	
	

	/**
	 * 회원가입 
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @param rttr {@link RedirectAttributes}
	 * 
	 * @return 글 목록 화면(redirect:/board/list)
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String userRegist(HttpServletRequest request, 
			@ModelAttribute("userVO") UserVO userVO, RedirectAttributes rttr) throws Exception {
    	
		//logger.info(">>>>>>> 회원가입 .......");

		service.insertUser(userVO, request);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}
	
	

	/**
	 * 사용자의 로그인 기록을 조회한다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 로그인 기록 화면(/user/UserHistory) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String userLoginHistory(HttpServletRequest request, Model model) throws Exception{

		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){
			
			UserVO userVO = (UserVO)sessionObj;
			
			//logger.info(">>>>>>> 로그인 기록 .......");
			
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
	
	
 
	/**
	 * 로그인 기록 화면에서 더보기 클릭 시 로그인 기록을 추가로 가져온다.
	 * 
	 * @param userno 사용자 번호
	 * @param start 가져올 로그인기록 페이지
	 * @param end 한번에 가져올 로그인 기록의 수
	 * 
	 * @return {@link ResponseEntity} 로그인 기록
	 * @throws Exception
	 */
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
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	

	/**
	 * 회원 정보를 본다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 회원정보 보기 화면(/user/UserInfo) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String userInfo(HttpServletRequest request, Model model) throws Exception{
		
		//logger.info(">>>>>>> 회원정보 보기 .......");
		
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
	
	

	/**
	 * 회원정보 수정 화면을 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 회원정보 수정 화면 (/user/UserModify)
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/update", method = RequestMethod.GET)
	public String userUpdateView(HttpServletRequest request, Model model) throws Exception{
			
		//logger.info(">>>>>>> 회원정보 수정 화면 .......");
			
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
	
	
	
	/**
	 * 회원정보를 수정한다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @param rttr {@link RedirectAttributes}
	 * 
	 * @return 회원정보 화면 (redirect:/user/info)
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String userUpdate(HttpServletRequest request, 
			@ModelAttribute("userVO") UserVO userVO, RedirectAttributes rttr) throws Exception{
			
		//logger.info(">>>>>>> 회원정보 수정 .......");

		service.updateUser(userVO, request);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/user/info";
	}
	

	/**
	 * 비밀번호 수정
	 * 
	 * @param userno 사용자 번호
	 * @param curtpw 현재 비밀번호
	 * @param newpw 새 비밀번호
	 * 
	 * @return {@link ResponseEntity} 수정결과 메시지
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public ResponseEntity<String> userPasswordUpdate(int userno, 
			String curtpw, String newpw) throws Exception {

		//logger.info(">>>>>>> 비밀번호 수정 ........");

		ResponseEntity<String> entity = null;
	
		try {
			
			String msg = service.updateUserPassword(userno, curtpw, newpw);
			entity = new ResponseEntity<String>(msg, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			// 비밀번호 수정 작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}	

	/**
	 * 회원 탈퇴
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * 
	 * @return {@link ResponseEntity} 탈퇴결과 메시지
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> userDelete(
			@ModelAttribute("userVO") UserVO userVO) throws Exception {

		//logger.info(">>>>>>> 회원 탈퇴 ........");

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

	

	/**
	 * 사용자의 활동내역(작성글, 댓글)을 조회한다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 활동내역 화면(/user/UserActivity) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String userActivity(HttpServletRequest request, Model model) throws Exception{

		HttpSession session = request.getSession();
		Object sessionObj =  session.getAttribute("login");
		
		if(sessionObj != null){

			UserVO userVO = (UserVO)sessionObj;
			UserVO userInfo = service.selectUserInfo(userVO.getUserno());
			
			model.addAttribute("userVO", userInfo);
				
			return "/user/UserActivity";
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	

	
	/**
	 * 아이디 중복체크를 한다.
	 * 
	 * @param userid 중복체크할 아이디
	 * 
	 * @return {@link ResponseEntity} 중복체크 결과
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkid", method = RequestMethod.GET)
	public ResponseEntity<Integer> checkUserID(String userid) throws Exception{
		
		ResponseEntity<Integer> entity = null;

		try {
			//logger.info(">>>>>>> 아이디 중복체크 ........");
			
			int result = service.selectDuplUserId(userid);	
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	/**
	 * 사용자관리 화면을 보여준다.
	 * 
	 * @param request {@link HttpServletRequest} 
	 * @param model {@link Model} 
	 * 
	 * @return 사용자 관리 화면(/user/UserManagement) 또는 로그인 화면(redirect:/view/login)
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgt", method = RequestMethod.GET)
	public String userManagementView(HttpServletRequest request, Model model) throws Exception{
		
		//logger.info(">>>>>>> 사용자 관리 화면 .......");
		
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
				// 로그인한 사용자가 관리자가 아닐경우
				return "redirect:/board/list";	
			}
		}
		
		// 로그인 하지 않고 접근할 경우 로그인 화면 리다이렉트
		return "redirect:/view/login";	
	}
	

	
	/**
	 * 사용자 관리 - 사용자 목록을 가져온다.
	 * 
	 * @param keyword 검색한 사용자 아이디 또는 이름
	 * 
	 * @return {@link ResponseEntity} 사용자 목록
	 * @throws Exception
	 */
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
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	

	/**
	 * 사용자관리 - 추가로드 클릭 시 사용자 목록 추가로 가져온다.
	 * 
	 * @param start 사용자 목록 시작 페이지
	 * @param end 한번에 가져올 사용자 목록 수
	 * @param flag 특정 조건에 해당하는 사용자 목록을 가져올 Flag값
	 * @param keyward 검색한 사용자 아이디 또는 이름
	 * 
	 * @return @return {@link ResponseEntity} 사용자 목록
	 * @throws Exception
	 */
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
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	/**
	 * 탈퇴한 사용자를 조회한다.
	 * 
	 * @return {@link ResponseEntity} 탈퇴한 사용자 목록
	 * @throws Exception
	 */
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
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	

	/**
	 * 계정이 잠긴 사용자를 조회한다.
	 * 
	 * @return {@link ResponseEntity} 계정이 잠긴 사용자 목록
	 * @throws Exception
	 */
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
			entity = new ResponseEntity<List<UserVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	/**
	 * 사용자 정보(계정잠금, 탈퇴여부)를 수정한다.
	 * 
	 * @param userList 수정할 사용자 List
	 * 
	 * @return {@link ResponseEntity} 수정결과 메시지
	 * @throws Exception
	 */
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

	

	/**
	 * 아이디를 찾는다.
	 * 
	 * @param username 사용자 이름
	 * @param email 사용자 이메일
	 * 
	 * @return {@link ResponseEntity} 사용자 아이디
	 * @throws Exception
	 */
	@RequestMapping(value = "/find/id", method = RequestMethod.GET)
	public ResponseEntity<String> findUserId(String username, String email) throws Exception{
		
		ResponseEntity<String> entity = null;

		try {
			logger.info(">>>>>>> 아이디 찾기 ........");
			
			String userid = service.selectUserId(username, email);	
			entity = new ResponseEntity<String>(userid, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	
	/**
	 * 비밀번호를 찾는다. 비밀번호 찾기 시 임시 비밀번호를 발급한다.
	 * 
	 * @param userid 사용자 아이디
	 * 
	 * @return {@link ResponseEntity} 임시 비밀번호
	 * @throws Exception
	 */
	@RequestMapping(value = "/find/pw", method = RequestMethod.GET)
	public ResponseEntity<String> findUserPw(String userid) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		try {
			logger.info(">>>>>>> 비밀번호 찾기 ........");
			
			String result = service.selectUserPw(userid);
			entity = new ResponseEntity<String>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	/******** 테스트 **********/
	
	// @PathVariable를 사용하면 500번 에러가 발생함
	// @PathVariable는 @RequestMapping(value = "/all/{brdno}" 이렇게 있다면 여기서 {brdno} 값을 가져와 변수에 담아준다.
	@RequestMapping(value = "/checkid2")
    public void chkUserid(HttpServletRequest request, HttpServletResponse response, String userid) {
       
		logger.info(">>>>>>> 아이디 중복체크 ........"+userid);
    }
}
