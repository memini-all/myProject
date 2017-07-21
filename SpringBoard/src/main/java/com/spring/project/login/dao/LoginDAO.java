package com.spring.project.login.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.common.util.Criteria;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.reply.dto.ReplyVO;
import com.spring.project.user.dto.UserVO;

public interface LoginDAO {

	/**
	 * 로그인 체크
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public UserVO login(LoginVO loginVO) throws Exception;
	
	/**
	 * 아이디가 존재하는지 체크한다.
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public boolean isExistId(String userID) throws Exception;
	
	/**
	 * 로그인 실패 횟수를 초기화 한다.
	 * @param userID
	 * @throws Exception
	 */
	public void resetFailCount(String userID) throws Exception;
	
	/**
	 * 로그인 실패시, 실패 횟수를 증가시킨다.
	 * @param userID
	 * @throws Exception
	 */
	public void plusFailCount(String userID) throws Exception;
	
	/**
	 * 로그인 실패 횟수를 조회한다.
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public int selectLoginFailCnt(String userID) throws Exception;
	
	/**
	 * 계정의 잠김 여부를 조회한다.
	 * @param userID
	 * @return
	 * @throws Exception
	 */
	public boolean selectAccountLock(String userID) throws Exception;
	
	
	/**
	 * 로그인 5회 실패시 계정을 잠근다.
	 * @param userID
	 * @throws Exception
	 */
	public void accountLock(String userID) throws Exception;
	
	/**
	 * 로그인/로그아웃 기록을 저장한다.
	 * @param loginMap - 사용자번호, ip, 상태(로그인/로그아웃) 
	 * @throws Exception
	 */
	public void insertLoginHistory(Map<String,Object> loginMap) throws Exception;
	
	/**
	 * 해당유저의 로그인기록을 조회한다.
	 * @param userno
	 * @throws Exception
	 */
	public List<LoginVO> selectLoginHistoryList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 로그인 기록 횟수를 조회한다.
	 * @param userno
	 * @return
	 * @throws Exception
	 */
	public int selectLoginCount(int userno) throws Exception;
}
