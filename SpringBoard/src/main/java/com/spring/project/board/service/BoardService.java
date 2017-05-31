package com.spring.project.board.service;

import com.spring.project.board.dto.BoardVO;

public interface BoardService {
	
	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public void boardRegist(BoardVO board) throws Exception;
}
