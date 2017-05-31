package com.spring.project.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.board.dao.BoardDAO;
import com.spring.project.board.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void boardRegist(BoardVO board) throws Exception {
	
		boardDAO.regist(board);
	}

}
