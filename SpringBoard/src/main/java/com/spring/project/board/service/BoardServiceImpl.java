package com.spring.project.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.board.dao.BoardDAO;
import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.dto.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void boardRegist(BoardVO board) throws Exception {
	
		boardDAO.regist(board);
	}

	@Override
	public List<BoardVO> boardList(Criteria cri) throws Exception {
			
		return boardDAO.boardList(cri);
	}

	@Override
	public BoardVO boardDetail(Integer brdno) throws Exception {
		
		return boardDAO.detail(brdno);
	}

	@Override
	public void boardModify(BoardVO board) throws Exception {
		
		boardDAO.modify(board);
	}

	@Override
	public void boardRemove(Integer brdno) throws Exception {
		
		boardDAO.remove(brdno);
	}

	@Override
	public int listCount(Criteria cri) throws Exception {
		
		return boardDAO.countPaging(cri);
	}

}
