package com.spring.project.reply.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.common.util.Criteria;
import com.spring.project.reply.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "com.spring.project.reply";
	
	@Override
	public List<ReplyVO> selectReplyList(Map<String, Object> paramMap) throws Exception {
		// Service로 코드 이동
		//Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("brdno", brdno);
		//paramMap.put("cri", cri);
		
		return sqlSession.selectList(namespace+".selectReplyList", paramMap);
	}

	@Override
	public int insertReply(ReplyVO replyVO) throws Exception {
	
		// insert 실행 후 AUTO_INCREMENT 값이 ReplyVO의 변수 repno에 세팅된다.
		sqlSession.insert(namespace+".insertReply", replyVO);
		
		// AUTO_INCREMENT(댓글 repno) 값 전달
		return replyVO.getRepno();	
	}
	
	@Override
	public void updateReplyGroup(Integer repno) throws Exception {

		sqlSession.update(namespace+".updateReplyGroup", repno);
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		
		sqlSession.update(namespace+".updateReply", replyVO);
	}

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

	// 테스트용..
	@Override
	public List<ReplyVO> listAll(Integer brdno) throws Exception {
		
		return sqlSession.selectList(namespace+".listAll", brdno);
	}

}
