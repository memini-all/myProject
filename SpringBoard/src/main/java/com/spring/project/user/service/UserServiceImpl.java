package com.spring.project.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.FileUtils;
import com.spring.project.common.util.UtilEtc;
import com.spring.project.login.dao.LoginDAO;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dao.UserDAO;
import com.spring.project.user.dto.UserVO;


/**
 * UserService의 구현체<br>
 * 사용자와 관련된 처리를 한다.
 * @author adm
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;

	@Inject
	private LoginDAO loginDAO;

	
	/**
	 * common-context.xml에 등록한 프로필 이미지 업로드 경로
	 */
	@Resource(name = "profileImgPath")
	private String profileImgPath;

	
	/**
	 * 파일업로드를 처리할 Utils 클래스
	 */
	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	
	@Transactional
	@Override
	public void insertUser(UserVO userVO, HttpServletRequest request) throws Exception {

		String imgFileName = fileUtils.profileImgFileInfo(profileImgPath, request);
		userVO.setProfile_Img(imgFileName);

		userDAO.insertUser(userVO);
	}

	
	@Override
	public int selectDuplUserId(String userid) throws Exception {

		return userDAO.selectDuplUserId(userid);
	}

	
	@Override
	public String selectProfileImg(int userno) throws Exception {

		return userDAO.selectProfileImg(userno);
	}

	
	@Override
	public List<LoginVO> selectLoginHistoryList(Criteria cri, int userno) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userno", userno);
		paramMap.put("cri", cri);

		return loginDAO.selectLoginHistoryList(paramMap);
	}

	
	@Override
	public int selectLoginCount(int userno) throws Exception {

		return loginDAO.selectLoginCount(userno);
	}

	
	@Override
	public UserVO selectUserInfo(int userno) throws Exception {

		return userDAO.selectUserInfo(userno);
	}

	
	@Transactional
	@Override
	public void updateUser(UserVO userVO, HttpServletRequest request) throws Exception {

		// 기존 프로필 이미지 파일명
		String existImgName = userVO.getProfile_Img();
		// 새로운 프로필 이미지 파일명
		String newImgName = fileUtils.profileImgFileInfo(profileImgPath, request);

		// 기존 이미지 파일명이 있을경우 그대로 사용
		if ( existImgName == null || existImgName.equals("") ) {
			userVO.setProfile_Img(newImgName);
		} 	
		
		userDAO.updateUser(userVO);
	}

	
	@Override
	public int selectDeleteUserInfo(UserVO userVO) throws Exception {
		
		return userDAO.selectDeleteUserInfo(userVO);
	}

	
	@Transactional
	@Override
	public String updateUserPassword(int userno, String curtpw, String newpw) throws Exception {
		
		String msg = "NOT_FOUND";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userno", userno);
		paramMap.put("userpw", curtpw);
		
		int result = userDAO.selectUserPassowrd(paramMap);
		
		// 화면에서 입력된 현재 비밀번호가 올바를경우
		if(result == 1){
			
			paramMap.replace("userpw", newpw);
			userDAO.updatetUserPassowrd(paramMap);
			msg = "SUCCESS";
		}

		return msg;
	}
	
	
	@Transactional
	@Override
	public void deleteUser(int userno) throws Exception {
		
		userDAO.deleteUser(userno);
	}

	
	@Override
	public List<UserVO> selectUserList(String keyword, Criteria cri) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("keyword", keyword);
		paramMap.put("cri", cri);
		
		return userDAO.selectUserList(paramMap);
	}

	
	@Override
	public int selectUserCount(String keyword) throws Exception {
	
		return userDAO.selectUserCount(keyword);
	}


	@Override
	public List<UserVO> selectUserAddition(String keyword, Criteria cri, int flag) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("keyword", keyword);
		paramMap.put("cri", cri);
		
		// 사용자 추가로드, 검색된 사용자 추가로드
		if(flag == 0){
			return userDAO.selectUserList(paramMap);
		}
		// 잠금된 사용자 추가로드
		else if(flag == 1){
			return userDAO.selectLockUser(cri);
		}
		// 탈퇴한 사용자 추가로드
		else if(flag == 2){
			return userDAO.selectWithdrawUser(cri);
		}
		
		return null;
	}
	
	
	@Override
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception {
		
		return userDAO.selectWithdrawUser(cri);
	}

	
	@Override
	public List<UserVO> selectLockUser(Criteria cri) throws Exception {
		
		return userDAO.selectLockUser(cri);
	}

	
	@Transactional
	@Override
	public void updateMgtUser(List<UserVO> userList) throws Exception {
		
		for(UserVO vo : userList){
			userDAO.updateMgtUser(vo);
		}		
	}

	
	@Override
	public String selectUserId(String username, String email) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("email", email);
		
		return userDAO.selectUserId(paramMap);
	}

	
	@Transactional
	@Override
	public String selectUserPw(String userid) throws Exception {
		
		UserVO userVO = userDAO.selectUserPw(userid);
		String result = "NOUSER";
		
		// 해당하는 사용자가 있을경우
		if(userVO != null){
			
			// 계정이 잠겨있는지 확인
			if(userVO.getIslock().equals("Y")){
				result = "LOCK";	
			}
			else{
				// 7자리의 임시비밀번호를 생성한다.
				String tempPw = UtilEtc.randomPw(7);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userno", userVO.getUserno());
				paramMap.put("userpw", tempPw);
				
				// 사용자의 비밀번호를 임시 비밀번호로 변경한다.
				userDAO.updatetUserTempPw(paramMap);
				
				result = tempPw;
			}
		}
		return result;
	}

	
}
