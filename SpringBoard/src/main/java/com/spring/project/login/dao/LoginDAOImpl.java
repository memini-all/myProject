package com.spring.project.login.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dto.UserVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "com.spring.project.login";

	@Override
	public UserVO login(LoginVO loginVO) throws Exception {

		return sqlSession.selectOne(namespace + ".loginCheck", loginVO);
	}

	@Override
	public boolean isExistId(String userID) throws Exception {

		return sqlSession.selectOne(namespace + ".isExistId", userID);
	}

	@Transactional
	@Override
	public void resetFailCount(String userID) throws Exception {

		sqlSession.update(namespace + ".resetFailCount", userID);
	}

	@Transactional
	@Override
	public void plusFailCount(String userID) throws Exception {

		sqlSession.update(namespace + ".plusFailCount", userID);
	}

	@Override
	public int selectLoginFailCnt(String userID) throws Exception {
		
		return sqlSession.selectOne(namespace + ".selectLoginFailCnt", userID);
	}

	@Override
	public boolean selectAccountLock(String userID) throws Exception {
		
		return sqlSession.selectOne(namespace + ".selectAccountLock", userID);
	}

	@Transactional
	@Override
	public void accountLock(String userID) throws Exception {
		
		sqlSession.update(namespace + ".accountLock", userID);
	}

	@Transactional
	@Override
	public void insertLoginHistory(Map<String, Object> loginMap) throws Exception {
	
		sqlSession.insert(namespace+".insertLoginHistory", loginMap);
	}


	@Override
	public List<LoginVO> selectLoginHistoryList(Map<String, Object> paramMap) throws Exception {
		
		return sqlSession.selectList(namespace + ".selectLoginHistoryList", paramMap);
	}

	@Override
	public int selectLoginCount(int userno) throws Exception {
		
		return sqlSession.selectOne(namespace + ".selectLoginCount", userno);
	}

}
