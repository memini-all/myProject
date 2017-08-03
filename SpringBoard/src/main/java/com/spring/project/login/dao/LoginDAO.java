package com.spring.project.login.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dto.UserVO;


/**
 * LoginDAO interface
 * @author adm
 *
 */
public interface LoginDAO {

	
	
	/**
	 * 로그인시 사용자 체크를 한다.
	 * 
	 * @param loginVO {@link LoginVO} 로그인 정보를 담고있는 VO
	 * 
	 * @return {@link UserVO} 사용자 VO
	 * @throws Exception
	 */
	public UserVO login(LoginVO loginVO) throws Exception;
	
	
	
	/**
	 * 아이디가 존재하는지 체크한다.
	 * 
	 * @param userID 사용자 아이디
	 * 
	 * @return boolean 아이디 존재여부
	 * @throws Exception
	 */
	public boolean isExistId(String userID) throws Exception;
	
	
	
	/**
	 * 로그인 실패 횟수를 초기화 한다.
	 * 
	 * @param userID 사용자 아이디
	 * @throws Exception
	 */
	public void resetFailCount(String userID) throws Exception;
	
	
	
	/**
	 * 로그인 실패 시 실패 횟수를 증가시킨다.
	 * 
	 * @param userID 사용자 아이디
	 * @throws Exception
	 */
	public void plusFailCount(String userID) throws Exception;
	
	
	
	/**
	 * 로그인 실패 횟수를 조회한다.
	 * 
	 * @param userID 사용자 아이디
	 * 
	 * @return int 로그인 실패 횟수
	 * @throws Exception
	 */
	public int selectLoginFailCnt(String userID) throws Exception;
	
	
	
	/**
	 * 계정의 잠김 여부를 조회한다.
	 * 
	 * @param userID 사용자 아이디
	 * 
	 * @return boolean 계정잠김여부
	 * @throws Exception
	 */
	public boolean selectAccountLock(String userID) throws Exception;
	
	
	
	/**
	 * 로그인 5회 실패시 계정을 잠근다.
	 * 
	 * @param userID 사용자 아이디
	 * @throws Exception
	 */
	public void accountLock(String userID) throws Exception;
	
	
	
	/**
	 * 로그인/로그아웃 기록을 저장한다.
	 * 
	 * @param loginMap - 사용자번호, ip, 상태(로그인/로그아웃) 
	 * @throws Exception
	 */
	public void insertLoginHistory(Map<String,Object> loginMap) throws Exception;
	

	
	/**
	 * 해당유저의 로그인기록을 조회한다.
	 * 
	 * @param paramMap 사용자 번호와 페이지 정보를 담고있는 Map
	 * 
	 * @return 사용자 로그인 정보 List
	 * @throws Exception
	 */
	public List<LoginVO> selectLoginHistoryList(Map<String, Object> paramMap) throws Exception;

	
	
	/**
	 * 로그인 기록 횟수를 조회한다.
	 * 
	 * @param userno 사용자 번호
	 * 
	 * @return 로그인기록의 수
	 * @throws Exception
	 */
	public int selectLoginCount(int userno) throws Exception;
}
