package com.spring.project.user.service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.FileUtils;
import com.spring.project.user.dao.UserDAO;
import com.spring.project.user.dto.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;
	
	@Resource(name = "profileImgPath")
	private String profileImgPath;
	
	@Resource(name="fileUtils")
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

	

}
