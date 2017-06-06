package com.spring.project.board.dao;

import java.util.List;

import com.spring.project.board.dto.BoardVO;

public interface BoardDAO {

	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public void regist(BoardVO board) throws Exception;
	
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
	public BoardVO detail(Integer brdno) throws Exception;
}
