package com.spring.project.reply.service;

import java.util.List;

import com.spring.project.common.util.Criteria;
import com.spring.project.reply.dto.ReplyVO;

public interface ReplyService {

	/**
	 * 댓글 목록
	 * @param brdno 글번호
	 * @return
	 * @throws Exception
	 */
	public List<ReplyVO> selectReplyList(Criteria cri, Integer brdno) throws Exception;
	
	
	/**
	 * 댓글 등록
	 * @param replyVO 
	 * @throws Exception
	 */
	public void insertReply(ReplyVO replyVO) throws Exception;
	
	/**
	 * 댓글 수정
	 * @param replyVO
	 * @throws Exception
	 */
	public void updateReply(ReplyVO replyVO) throws Exception;
	
	/**
	 * 댓글 삭제
	 * @param repno 댓글번호
	 * @throws Exception
	 */
	public void deleteReply(Integer repno) throws Exception;
	
	/**
	 * 게시글에 있는 댓글의 총 수를 구한다.
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public int replyCount(Integer brdno) throws Exception;
	
	
	/**
	 * 테스트용
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public List<ReplyVO> listAll(Integer brdno) throws Exception;
	
}
