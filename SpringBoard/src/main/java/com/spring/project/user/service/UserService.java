package com.spring.project.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.project.common.util.Criteria;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dto.UserVO;

public interface UserService {

	/**
	 * 회원정보 저장
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUser(UserVO userVO, HttpServletRequest request) throws Exception;
	
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
	 * 해당유저의 로그인기록을 조회한다.
	 * @param userno
	 * @throws Exception
	 */
	public List<LoginVO> selectLoginHistoryList(Criteria cri, int userno) throws Exception;
	
	/**
	 * 로그인 기록 횟수를 조회한다.
	 * @param userno
	 * @return
	 * @throws Exception
	 */
	public int selectLoginCount(int userno) throws Exception;
	
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
	public void updateUser(UserVO userVO, HttpServletRequest request) throws Exception;
	
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
	 * @param keyword 사용자 아이디 또는 이름
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectUserList(String keyword, Criteria cri) throws Exception;
	
	
	/**
	 * 관리자 모드 - 사용자 수를 조회한다.
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public int selectUserCount(String keyword) throws Exception;
	
	
	/**
	 * 관리자모드 - 사용자 정보를 추가로 가져온다.
	 * @param keyword 사용자 아이디 또는 이름
	 * @param cri
	 * @param falg 추가로드, 잠금회원, 탈퇴회원을 구분하기위한 flag
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectUserAddition(String keyword, Criteria cri, int flag) throws Exception;
	
	
	/**
	 * 관리자 모드 - 탈퇴한 사용자를 조회한다.
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception;
	
	
	/**
	 * 관리자 모드 - 계정이 잠금된 사용자를 조회한다.
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
	public void updateMgtUser(List<UserVO> userList) throws Exception;
	
	
	/**
	 * 입력한 이름과 이메일로 아이디를 찾는다.
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public String selectUserId(String username, String email) throws Exception;
	
	
	/**
	 * 입력한 아이디로 비밀번호를 찾는다.
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public String selectUserPw(String userid) throws Exception;
}
