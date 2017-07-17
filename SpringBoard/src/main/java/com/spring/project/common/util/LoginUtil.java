package com.spring.project.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginUtil {

	private static final Logger logger = LoggerFactory.getLogger(LoginUtil.class);

	/**
	 * Client의 IP주소를 가져온다.
	 * 
	 * @param request
	 * @return
	 */
	private String getIp(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");

		logger.info(">>>> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info(">>>> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info(">>>> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.info(">>>> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		
		logger.info(">>>> Result : IP Address : "+ip);

		return ip;

	}
	
	/**
	 * 로그인 정보를 담은 Map을 생성한다.
	 * @param userno 사용자 번호
	 * @param request HttpServletRequest
	 * @param type 유형 - 로그인/로그아웃
	 * @return
	 */
	public Map<String, Object> getLoginHistoryMap(int userno, HttpServletRequest request, String type) {

		String ip = getIp(request);
		String loginType = type.equals("I") ? "LOGIN" : "LOGOUT";
			
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap.put("userno", userno);
		loginMap.put("ip", ip);
		loginMap.put("type", loginType);
		
		return loginMap;
    }

}
