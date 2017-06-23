package com.spring.project.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.common.util.Criteria;
import com.spring.project.reply.dao.ReplyDAO;
import com.spring.project.reply.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDAO;
	
	@Override
	public List<ReplyVO> replyList(Criteria cri, Integer brdno) throws Exception {

		return replyDAO.replyList(cri, brdno);
	}

	@Transactional
	@Override
	public void replyRegist(ReplyVO replyVO) throws Exception {
	
		int repno = replyDAO.replyRegist(replyVO);
		
		// 답글이 아닌 일반 댓글일 경우 그룹변호 = 댓글번호로 만든다
		if(replyVO.getRgroup() == 0){
			replyDAO.updateReplyGroup(repno);
		}
	}

	@Transactional
	@Override
	public void replyModify(ReplyVO replyVO) throws Exception {
		
		replyDAO.replyModify(replyVO);
	}

	@Transactional
	@Override
	public void replyRemove(Integer repno) throws Exception {
		
		replyDAO.replyRemove(repno);
	}

	@Override
	public int replyCount(Integer brdno) throws Exception {
		
		return replyDAO.replyCount(brdno);
	}

	// 테스트용
	@Override
	public List<ReplyVO> listAll(Integer brdno) throws Exception {
		
		return replyDAO.listAll(brdno);
	}

}
