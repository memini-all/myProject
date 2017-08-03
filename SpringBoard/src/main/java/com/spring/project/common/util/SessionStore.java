package com.spring.project.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 로그인한 사용자의 Session정보를 관리한다.
 * @author adm
 *
 */
public class SessionStore {

	// private static volatile SessionStore sessionStore;
	private Map<String, HttpSession> loginSessions;

	
	// private으로 설정
	// 생성자를 호출하지 못하도록 막는다.
	private SessionStore() {
		loginSessions = new HashMap<String, HttpSession>();
	}

	
	
	/**
	 * 로그인한 사용자의 세션을 Map에 저장한다.
	 * 
	 * @param userId 사용자 아이디
	 * @param session {@link HttpSession} 로그인한 사용자의 세션
	 */
	public void addSession(String userId, HttpSession session) {
		loginSessions.put(userId, session);
	}

	
	
	/**
	 * 사용자 아이디에 해당하는 세션을 가져온다.
	 * 
	 * @param userId 사용자 아이디
	 * @return {@link HttpSession} 사용자의 세션
	 */
	public HttpSession get(String userId) {
		return loginSessions.get(userId);
	}

	
	
	/**
	 * 사용자 아이디에 해당하는 세션을 Map에서 삭제한다.
	 * 
	 * @param userId 사용자 아이디
	 */
	public void removeSession(String userId) {
		if (loginSessions.containsKey(userId)) {
			
			try {
				
				loginSessions.get(userId).invalidate();
				
			} catch (RuntimeException re) {
			}
			loginSessions.remove(userId);
		}
	}
}
