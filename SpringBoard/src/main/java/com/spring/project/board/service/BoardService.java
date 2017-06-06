package com.spring.project.board.service;

import java.util.List;

import com.spring.project.board.dto.BoardVO;

public interface BoardService {
	
	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public void boardRegist(BoardVO board) throws Exception;
	
	/**
	 * 글 목록
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> boardList() throws Exception;
	
	/**
	 * 글 상세보기
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public BoardVO boardDetail(Integer brdno) throws Exception;
}
