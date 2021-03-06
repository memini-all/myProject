package com.spring.project.login.service;

import java.util.Map;

import com.spring.project.login.dto.LoginVO;

/**
 * LoginService interface
 * @author adm
 *
 */
public interface LoginService {
	
	
	/**
	 * 로그인시 사용자 체크를 한다.
	 * 
	 * @param loginVO {@link LoginVO} 로그인 정보를 담고있는 VO
	 * 
	 * @return 로그인한 사용자 정보와 로그인 메시지를 담은 Map
	 * @throws Exception
	 */
	public Map<String, Object> login(LoginVO loginVO) throws Exception;
	
	
	
	/**
	 * 로그인/로그아웃 기록을 저장한다.
	 * 
	 * @param loginMap - 사용자번호, ip, 상태(로그인/로그아웃) 
	 * @throws Exception
	 */
	public void insertLoginHistory(Map<String,Object> loginMap) throws Exception;

}
