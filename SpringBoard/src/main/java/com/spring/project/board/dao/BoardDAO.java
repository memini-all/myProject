package com.spring.project.board.dao;

import com.spring.project.board.dto.BoardVO;

public interface BoardDAO {

	public void regist(BoardVO board) throws Exception;
}
