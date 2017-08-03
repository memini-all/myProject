package com.spring.project.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.project.common.util.Criteria;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dto.UserVO;


/**
 * UserService interface
 * @author adm
 *
 */
public interface UserService {

	
	/**
	 * 사용자를 등록한다.
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @param request {@link HttpServletRequest}
	 * @throws Exception
	 */
	public void insertUser(UserVO userVO, HttpServletRequest request) throws Exception;
	
	
	
	/**
	 * 아이디 중복체크를 한다.
	 * 
	 * @param userid 사용자 아이디
	 * @return int 중복체크 결과
	 * @throws Exception
	 */
	public int selectDuplUserId(String userid) throws Exception;
	
	
	
	/**
	 * 프로필 이미지를 조회한다.
	 * 
	 * @param userno 사용자 번호
	 * @return String 프로필 이미지 파일명
	 * @throws Exception
	 */
	public String selectProfileImg(int userno) throws Exception;
	
	
	
	/**
	 * 해당 유저의 로그인기록을 조회한다. 조회시 한번에 몇개의 로그인 기록을 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 로그인 기록을 가져온다.
	 * 
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * @param userno 사용자 번호
	 * @return 로그인 기록 List
	 * @throws Exception
	 */
	public List<LoginVO> selectLoginHistoryList(Criteria cri, int userno) throws Exception;
	
	
	
	/**
	 * 사용자의 로그인 기록의 총 개수를 조회한다.
	 * 
	 * @param userno 사용자 번호
	 * @return int 로그인 기록의 수
	 * @throws Exception
	 */
	public int selectLoginCount(int userno) throws Exception;
	
	
	
	/**
	 * 사용자 정보를 조회한다.
	 * 
	 * @param userno 사용자 번호
	 * @return {@link UserVO} 사용자 정보
	 * @throws Exception
	 */
	public UserVO selectUserInfo(int userno) throws Exception;
	
	
	
	/**
	 * 사용자 정보를 수정한다.
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @param request {@link HttpServletRequest}
	 * @throws Exception
	 */
	public void updateUser(UserVO userVO, HttpServletRequest request) throws Exception;
	
	
	
	/**
	 * 탈퇴할 사용자 정보를 확인한다. 조회결과가 0 이라면 사용자 정보 잘못됨(해당하는 사용자가 DB에 없음)
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @return int 탈퇴정보
	 * @throws Exception
	 */
	public int selectDeleteUserInfo(UserVO userVO) throws Exception;
	
	
	
	/**
	 * 회원탈퇴
	 * 
	 * @param userno 탈퇴할 사용자 번호
	 * @throws Exception
	 */
	public void deleteUser(int userno) throws Exception;
	
	
	
	/**
	 * 관리자모드 - 사용자를 조회한다. 조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param keyword 사용자 아이디 또는 이름
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * 
	 * @return 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectUserList(String keyword, Criteria cri) throws Exception;
	
	
	
	/**
	 * 관리자 모드 - 사용자 수를 조회한다.
	 * 
	 * @param keyword 사용자 아이디 또는 이름
	 * @return int 사용자 수
	 * @throws Exception
	 */
	public int selectUserCount(String keyword) throws Exception;
	

	
	/**
	 * 관리자모드 - 사용자 정보를 추가로 가져온다. 조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param keyword 사용자 아이디 또는 이름
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * @param flag 추가로드, 잠금회원, 탈퇴회원을 구분하기위한 flag
	 * 
	 * @return 조건에 해당하는 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectUserAddition(String keyword, Criteria cri, int flag) throws Exception;
	
	
	
	/**
	 * 관리자 모드 - 탈퇴한 사용자를 조회한다. 조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * 
	 * @return 탈퇴한 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception;
	
	
	
	/**
	 * 관리자 모드 - 계정이 잠금된 사용자를 조회한다.  조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * 
	 * @return 계정이 잠긴 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectLockUser(Criteria cri) throws Exception;
	
	
	
	/**
	 * 관리자 모드 - 사용자 정보를 수정한다.
	 * 
	 * @param userList 수정할 사용자 목록
	 * @throws Exception
	 */
	public void updateMgtUser(List<UserVO> userList) throws Exception;
	
	
	
	/**
	 * 입력한 이름과 이메일로 아이디를 찾는다.
	 * 
	 * @param username 사용자 이름
	 * @param email 사용자 이메일
	 * @return String 사용자 아이디
	 * @throws Exception
	 */
	public String selectUserId(String username, String email) throws Exception;
	
	
	
	/**
	 * 입력한 아이디로 비밀번호를 찾는다.
	 * 
	 * @param userid 사용자 아이디
	 * @return {@link UserVO} 사용자 정보가 담긴 VO
	 * @throws Exception
	 */
	public String selectUserPw(String userid) throws Exception;
}
