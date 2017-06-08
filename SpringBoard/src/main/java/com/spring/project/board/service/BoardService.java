package com.spring.project.board.service;

import java.util.List;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.dto.Criteria;

public interface BoardService {
	
	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public void boardRegist(BoardVO board) throws Exception;
	
	/**
	 * 글 목록 - 페이지 처리
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> boardList(Criteria cri) throws Exception;
	
	/**
	 * 글 상세보기
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public BoardVO boardDetail(Integer brdno) throws Exception;
	
	/**
	 * 글 수정
	 * @param board
	 * @throws Exception
	 */
	public void boardModify(BoardVO board) throws Exception;
	
	/**
	 * 글 삭제
	 * @param brdno
	 * @throws Exception
	 */
	public void boardRemove(Integer brdno) throws Exception;
	
	/**
	 * 글을 총 개수
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int listCount(Criteria cri) throws Exception;
}
