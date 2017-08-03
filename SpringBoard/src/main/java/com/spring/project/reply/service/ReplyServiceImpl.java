package com.spring.project.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.reply.dao.ReplyDAO;
import com.spring.project.reply.dto.ReplyVO;

/**
 * ReplyService의 구현체<br>
 * 댓글과 관련된 처리를 한다.
 * @author adm
 *
 */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDAO;
	
	
	@Override
	public List<ReplyVO> selectReplyList(Criteria cri, Integer brdno) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("brdno", brdno);
		paramMap.put("cri", cri);
		
		return replyDAO.selectReplyList(paramMap);
	}

	
	@Transactional
	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
	
		int repno = replyDAO.insertReply(replyVO);
		
		// 답글이 아닌 일반 댓글일 경우 그룹변호 = 댓글번호로 만든다
		if(replyVO.getRgroup() == 0){
			replyDAO.updateReplyGroup(repno);
		}
	}

	
	@Transactional
	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		
		replyDAO.updateReply(replyVO);
	}

	
	@Transactional
	@Override
	public void deleteReply(Integer repno) throws Exception {
		
		replyDAO.deleteReply(repno);
	}

	
	@Override
	public int replyCount(Integer brdno) throws Exception {
		
		return replyDAO.replyCount(brdno);
	}

	
	@Override
	public int selectUserReplyCnt(int userno) throws Exception {
		
		return replyDAO.selectUserReplyCnt(userno);
	}

	
	@Override
	public List<ReplyVO> selectUserReplyList(Criteria cri, int userno) throws Exception {
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userno", userno);
		paramMap.put("cri", cri);
		
		return replyDAO.selectUserReplyList(paramMap);
	}

	
	@Transactional
	@Override
	public void deleteUserReply(List<Integer> repnoList) throws Exception {
		
		replyDAO.deleteUserReply(repnoList);
	}

}
