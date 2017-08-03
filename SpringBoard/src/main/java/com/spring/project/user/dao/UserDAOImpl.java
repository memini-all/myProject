package com.spring.project.user.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.user.dto.UserVO;


/**
 * UserDAO의 구현체<br>
 * 사용자 테이블과 관련된 CRUD를 처리한다.
 * @author adm
 *
 */
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
	public int selectDuplUserId(String userid) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectDuplUserId", userid);
	}


	@Override
	public String selectProfileImg(int userno) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectProfileImg", userno);
	}


	@Override
	public UserVO selectUserInfo(int userno) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserInfo", userno);
	}

	
	@Transactional
	@Override
	public void updateUser(UserVO userVO) throws Exception {
		
		sqlSession.update(namespace+".updateUser", userVO);
	}


	@Override
	public int selectDeleteUserInfo(UserVO userVO) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectDeleteUserInfo", userVO);
	}

	
	@Transactional
	@Override
	public void deleteUser(int userno) throws Exception {
		
		sqlSession.delete(namespace+".deleteUser", userno);
	}


	@Override
	public List<UserVO> selectUserList(Map<String, Object> paramMap) throws Exception {
		
		return sqlSession.selectList(namespace+".selectUserList", paramMap);
	}


	@Override
	public int selectUserCount(String keyword) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserCount", keyword);
	}


	@Override
	public List<UserVO> selectWithdrawUser(Criteria cri) throws Exception {
		
		return sqlSession.selectList(namespace+".selectWithdrawUser", cri);
	}


	@Override
	public List<UserVO> selectLockUser(Criteria cri) throws Exception {
		
		return sqlSession.selectList(namespace+".selectLockUser", cri);
	}

	
	@Transactional
	@Override
	public void updateMgtUser(UserVO userVO) throws Exception {
		
		sqlSession.update(namespace+".updateMgtUser", userVO);
	}


	@Override
	public String selectUserId(Map<String, Object> paramMap) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserId", paramMap);
	}


	@Override
	public UserVO selectUserPw(String userid) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserPw", userid);
	}


	@Transactional
	@Override
	public void updatetUserPw(Map<String, Object> paramMap) throws Exception {
		
		sqlSession.update(namespace+".updatetUserPw", paramMap);
	}


}
