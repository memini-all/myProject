package com.spring.project.reply.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.reply.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.spring.project.reply";
	
	
	@Override
	public List<ReplyVO> selectReplyList(Map<String, Object> paramMap) throws Exception {
		
		return sqlSession.selectList(namespace+".selectReplyList", paramMap);
	}

	
	@Transactional
	@Override
	public int insertReply(ReplyVO replyVO) throws Exception {
	
		// insert 실행 후 AUTO_INCREMENT 값이 ReplyVO의 변수 repno에 세팅된다.
		sqlSession.insert(namespace+".insertReply", replyVO);
		
		// AUTO_INCREMENT(댓글 repno) 값 전달
		return replyVO.getRepno();	
	}
	
	
	@Transactional
	@Override
	public void updateReplyGroup(Integer repno) throws Exception {

		sqlSession.update(namespace+".updateReplyGroup", repno);
	}

	
	@Transactional
	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		
		sqlSession.update(namespace+".updateReply", replyVO);
	}

	
	@Transactional
	@Override
	public void deleteReply(Integer repno) throws Exception {
		
		sqlSession.delete(namespace+".deleteReply", repno);
	}


	@Override
	public int replyCount(Integer brdno) throws Exception {
			
		return sqlSession.selectOne(namespace+".replyCount", brdno);
	}

	
	@Override
	public int checkReplyChild(Integer repno) throws Exception {
		
		return sqlSession.selectOne(namespace+".checkChild", repno);
	}

	
	@Override
	public int selectUserReplyCnt(int userno) throws Exception {
		
		return sqlSession.selectOne(namespace+".selectUserReplyCnt", userno);
	}

	
	@Override
	public List<ReplyVO> selectUserReplyList(Map<String, Object> paramMap) throws Exception {
		
		return sqlSession.selectList(namespace+".selectUserReplyList", paramMap);
	}

	@Transactional
	@Override
	public void deleteUserReply(List<Integer> repnoList) throws Exception {
		
		sqlSession.update(namespace+".deleteUserReply", repnoList);
	}
	
}
