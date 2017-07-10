package com.spring.project.login.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.project.common.util.SessionStore;
import com.spring.project.user.dto.UserVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "sessionStore")
	private SessionStore sessionStore;

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// HttpSession session = request.getSession();

		// 세션에 정보가 남아있을 경우 삭제
		// if(session.getAttribute(LOGIN) != null){
		// logger.info(">>>>>>> preHandle : clear login data before");
		// session.removeAttribute(LOGIN);
		// }

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();

		// UserController 에서 담아둔 userVO 객체를 가져온다.
		ModelMap modelMap = modelAndView.getModelMap();
		UserVO userVO = (UserVO) modelMap.get("userVO");

		// 가져온 객체를 세션에 담는다.
		if (userVO != null) {

			logger.info(">>>>>>> postHandle : new login success");

			// 이미 로그인 되어 있다면, 기존 로그인 세션을 종료
			if (sessionStore.get(userVO.getUserid()) != null) {
				logger.info(">>>>>>> 중복로그인 방지");
				sessionStore.removeSession(userVO.getUserid());
			}
			
			// 새로운 로그인 세션 입력
			sessionStore.addSession(userVO.getUserid(), session);
			session.setAttribute(LOGIN, userVO);

			// 쿠키 생성
			if (request.getParameter("useCookie") != null) {

				logger.info(">>>>>>> 자동로그인....");
				Cookie loginCookie = new Cookie("loginCookie", userVO.getUserid());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키의 유효시간은 초단위, (60 * 60 * 24 * 7) = 1주일
				response.addCookie(loginCookie);
			}
			
			response.sendRedirect("/"); // => 리다이렉트에 메인화면으로 이동
		}
	}

}
