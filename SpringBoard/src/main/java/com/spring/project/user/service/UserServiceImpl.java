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
import com.spring.project.login.dao.LoginDAO;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dao.UserDAO;
import com.spring.project.user.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;

	@Inject
	private LoginDAO loginDAO;

	@Resource(name = "profileImgPath")
	private String profileImgPath;

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
	public int selectUserId(String userid) throws Exception {

		return userDAO.selectUserId(userid);
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
	public void deleteUser(int userno) throws Exception {
		
		userDAO.deleteUser(userno);
	}

	
}
