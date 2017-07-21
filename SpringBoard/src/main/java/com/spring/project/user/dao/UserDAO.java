package com.spring.project.user.dao;

import com.spring.project.user.dto.UserVO;

public interface UserDAO {
	
	/**
	 * 회원정보 저장
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUser(UserVO userVO) throws Exception;
	
	
	/**
	 * 사용자 아이디 조회 - 아이디 중복체크
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int selectUserId(String userid) throws Exception;
	
	
	/**
	 * 프로필 이미지 조회
	 * @param userno
	 * @return
	 * @throws Exception
	 */
	public String selectProfileImg(int userno) throws Exception;
	
	
	/**
	 * 회원정보를 조회한다.
	 * @param userno
	 * @return
	 * @throws Exception
	 */
	public UserVO selectUserInfo(int userno) throws Exception;
	
	
	/**
	 * 회원정보 수정
	 * @param userVO
	 * @throws Exception
	 */
	public void updateUser(UserVO userVO) throws Exception;
	
	/**
	 * 탈퇴할 회원의 비밀번호를 확인한다.
	 * @param userVO
	 * @throws Exception
	 */
	public int selectDeleteUserInfo(UserVO userVO) throws Exception;
	

	/**
	 * 회원탈퇴
	 * @param userno
	 * @throws Exception
	 */
	public void deleteUser(int userno) throws Exception;
}
