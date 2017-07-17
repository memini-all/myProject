package com.spring.project.login.controller;


import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.project.common.util.LoginUtil;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.login.service.LoginService;
import com.spring.project.user.dto.UserVO;


@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name="loginUtil")
	private LoginUtil loginUtil;
	
	@Inject
	private LoginService service;
	
	@RequestMapping(value = "/view/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("loginVO") LoginVO loginVO){
		return "/login/Login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(LoginVO loginVO, HttpServletRequest request, HttpSession session, Model model, RedirectAttributes rttr) throws Exception{
		
		logger.info(">>>>>>>>>>>> login 실행");
		
		Map<String, Object> resultMap = service.login(loginVO);	
		String result = (String)resultMap.get("msg");
		
		// 1. 계정이 없을 경우, 혹은 비밀번호가 틀릴경우 
		if(result.equals("FAIL")){
			rttr.addFlashAttribute("msg", result);
			return "redirect:/view/login";			// 로그인 화면으로 리다이렉트
		}
		
		// 2. 계정이 잠겨있을 경우 로그인 불가
		if(result.equals("LOCK")){
			rttr.addFlashAttribute("msg", result);
			return "redirect:/board/list";			// 메인화면으로 리다이렉트
		}

		UserVO userVO = (UserVO)resultMap.get("userVO");
		
		// 3. 로그인 성공시
		model.addAttribute("userVO", userVO);
		
		// 4. 로그인 기록을 저장
		Map<String, Object> loginMap = loginUtil.getLoginHistoryMap(userVO.getUserno(), request, "I");
		service.insertLoginHistory(loginMap);
		
		return "board/BoardList";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Object obj = session.getAttribute("login");
		
		// 로그인 되어있는 상태라면..
		if(obj != null){
			
			logger.info(">>>>>>>>>>>> logout 실행");
			
			UserVO userVO = (UserVO)obj;
			
			session.removeAttribute("login"); // 세션에서 정보 삭제
			session.invalidate();
			
			// 로그아웃 기록을 저장
			Map<String, Object> loginMap = loginUtil.getLoginHistoryMap(userVO.getUserno(), request, "O");
			service.insertLoginHistory(loginMap);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			// 쿠키삭제
			if(loginCookie != null){
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}	
		}

		return "redirect:/board/list";
	}

}
