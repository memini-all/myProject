package com.spring.project.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.project.board.dao.BoardDAO;
import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.dto.Criteria;
import com.spring.project.board.dto.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public void boardRegist(BoardVO board) throws Exception {
	
		boardDAO.regist(board);
	}

	@Override
	public List<BoardVO> boardList(SearchCriteria cri) throws Exception {

		// vo에서 Regdate 시간값을 못가져옴
//		List<BoardVO> voList = boardDAO.boardList(cri);	
//		System.out.println("\n서비스 : "+voList.get(0).getTitle()+" == "+voList.get(0).getRegdate()+"\n");						
//		return voList;
		
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
	public int listCount(SearchCriteria cri) throws Exception {
		
		return boardDAO.countPage(cri);
	}

}
