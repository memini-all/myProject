package com.spring.project.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.user.dto.UserVO;


/**
 * UserDAO interface
 * @author adm
 *
 */
public interface UserDAO {
	
	
	/**
	 * 사용자를 등록한다.
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @throws Exception
	 */
	public void insertUser(UserVO userVO) throws Exception;
	
	
	
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
	 * @throws Exception
	 */
	public void updateUser(UserVO userVO) throws Exception;
	
	
	
	/**
	 * 탈퇴할 사용자 정보를 확인한다. 조회결과가 0 이라면 사용자 정보 잘못됨(해당하는 사용자가 DB에 없음)
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @throws Exception
	 */
	public int selectDeleteUserInfo(UserVO userVO) throws Exception;
	

	/**
	 * 사용자의 현재 비밀번호를 체크한다. (비밀번호 수정에 사용)
	 * 
	 * @param paramMap 사용자번호와 현재 비밀번호를 담은 Map
	 * @return int 체크 결과
	 * @throws Exception
	 */
	public int selectUserPassowrd(Map<String, Object> paramMap) throws Exception;
	

	/**
	 * 사용자의 비밀번호를 수정한다.
	 * 
	 * @param paramMap 사용자번호와 변경할 비밀번호를 담은 Map
	 * @throws Exception
	 */
	public void updatetUserPassowrd(Map<String, Object> paramMap) throws Exception;
	
	
	/**
	 * 회원탈퇴
	 * 
	 * @param userno 탈퇴할 사용자 번호
	 * @throws Exception
	 */
	public void deleteUser(int userno) throws Exception;
	
	
	
	/**
	 * 관리자모드 - 사용자를 조회한다.
	 * 
	 * @param paramMap 사용자 검색 키워드와 페이지 정보를 담은 Map
	 * @return 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectUserList(Map<String, Object> paramMap) throws Exception;
	
	
	
	/**
	 * 관리자모드 - 사용자 수를 조회한다.
	 * 
	 * @param keyword 검색할 사용자 아이디 또는 이름
	 * @return int 조건에 해당하는 사용자 수
	 * @throws Exception
	 */
	public int selectUserCount(String keyword) throws Exception;
	
	
	
	/**
	 * 관리자모드 - 탈퇴한 사용자를 조회한다. 조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * @return 탈퇴한 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception;
	
	
	
	/**
	 * 관리자모드 - 계정이 잠금된 사용자를 조회한다. 조회시 한번에 몇 명의 사용자 정보를 가져올 것인지,
	 * 몇 번에 나눠서 가져올것인지를 알기위해 {@link Criteria} 클래스가 필요하다. 
	 * 즉 글 목록 페이지 처리하듯 사용자 정보를 가져온다.
	 * 
	 * @param cri {@link Criteria} 페이지 정보를 담고있는 클래스
	 * @return 계정이 잠긴 사용자 List
	 * @throws Exception
	 */
	public List<UserVO> selectLockUser(Criteria cri) throws Exception;
	
	
	
	/**
	 * 관리자 모드 - 사용자 정보를 수정한다.
	 * 
	 * @param userVO {@link UserVO} 사용자 정보를 담고있는 VO
	 * @throws Exception
	 */
	public void updateMgtUser(UserVO userVO) throws Exception;
	
	
	
	
	/**
	 * 입력한 이름과 이메일로 아이디를 찾는다.
	 * 
	 * @param paramMap 사용자 아이디와 이메일이 담긴 Map
	 * @return String 사용자 아이디
	 * @throws Exception
	 */
	public String selectUserId(Map<String, Object> paramMap) throws Exception;
	
	
	
	/**
	 * 입력한 아이디로 비밀번호를 찾는다.
	 * 
	 * @param userid 사용자 아이디
	 * @return {@link UserVO} 사용자 정보가 담긴 VO
	 * @throws Exception
	 */
	public UserVO selectUserPw(String userid) throws Exception;
	
	
	
	/**
	 * 비밀번호를 찾고 만든 임시 비밀번호로 비밀번호를 수정한다.
	 * 
	 * @param paramMap 사용자 번호와 임시비밀번호를 갖고있는 Map
	 * @throws Exception
	 */
	public void updatetUserTempPw(Map<String, Object> paramMap) throws Exception;
}
