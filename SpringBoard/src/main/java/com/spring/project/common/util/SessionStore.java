package com.spring.project.common.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionStore {

	// private static volatile SessionStore sessionStore;
	private Map<String, HttpSession> loginSessions;

	// private으로 설정
	// 생성자를 호출하지 못하도록 막는다.
	private SessionStore() {
		loginSessions = new HashMap<String, HttpSession>();
	}

	// public static synchronized SessionM​SessionStore getInstance() {
	//
	// if (sessionStore == null) {
	// sessionStore = new SessionStore();
	// }
	//
	// return sessionStore;
	// }

	public void addSession(String userId, HttpSession session) {
		loginSessions.put(userId, session);
	}

	public HttpSession get(String userId) {
		return loginSessions.get(userId);
	}

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
