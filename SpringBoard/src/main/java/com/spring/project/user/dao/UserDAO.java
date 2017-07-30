package com.spring.project.user.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.common.util.Criteria;
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
	public int selectDuplUserId(String userid) throws Exception;
	
	
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
	
	
	/**
	 * 관리자모드 - 사용자를 조회한다.
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectUserList(Map<String, Object> paramMap) throws Exception;
	
	
	/**
	 * 관리자모드 - 사용자 수를 조회한다.
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public int selectUserCount(String keyword) throws Exception;
	
	
	/**
	 * 관리자모드 - 탈퇴한 사용자를 조회한다.
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception;
	
	
	/**
	 * 관리자모드 - 계정이 잠금된 사용자를 조회한다.
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectLockUser(Criteria cri) throws Exception;
	
	
	/**
	 * 관리자 모드 - 사용자 정보를 수정한다.
	 * @param userList
	 * @throws Exception
	 */
	public void updateMgtUser(UserVO userVO) throws Exception;
	
	
	
	/**
	 * 입력한 이름과 이메일로 아이디를 찾는다.
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String selectUserId(Map<String, Object> paramMap) throws Exception;
	
	
	/**
	 * 입력한 아이디로 비밀번호를 찾는다.
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public UserVO selectUserPw(String userid) throws Exception;
	
	
	/**
	 * 비밀번호를 수정한다. - 비밀번호 찾기
	 * @param paramMap
	 * @throws Exception
	 */
	public void updatetUserPw(Map<String, Object> paramMap) throws Exception;
}
