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

	

}
