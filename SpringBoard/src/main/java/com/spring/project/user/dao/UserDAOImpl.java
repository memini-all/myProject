package com.spring.project.user.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.user.dto.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.spring.project.user";
	
	@Transactional
	@Override
	public void insertUser(UserVO userVO) throws Exception {
		
		sqlSession.insert(namespace+".insertUser", userVO);
	}

	
	@Override
	public int selectUserId(String userid) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserId", userid);
	}


	@Override
	public String selectProfileImg(int userno) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectProfileImg", userno);
	}

	
}
