package com.spring.project.reply.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.reply.dto.ReplyVO;

public interface ReplyDAO {

	/**
	 * 댓글 목록
	 * @param brdno 글번호
	 * @return
	 * @throws Exception
	 */
	public List<ReplyVO> selectReplyList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 댓글 등록
	 * @param replyVO 
	 * @throws Exception
	 */
	public int insertReply(ReplyVO replyVO) throws Exception;
	
	/**
	 * 댓글 그룹번호 수정
	 * @param repno
	 * @throws Exception
	 */
	public void updateReplyGroup(Integer repno) throws Exception;
	
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
	 * @param brdno 글번호
	 * @return
	 * @throws Exception
	 */
	public int replyCount(Integer brdno) throws Exception;
	
	/**
	 * 자식여부 체크
	 * @param repno
	 * @return
	 * @throws Exception
	 */
	public int checkReplyChild(Integer repno) throws Exception;
	
	
	/**
	 * 테스트용
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public List<ReplyVO> listAll(Integer brdno) throws Exception;
}
