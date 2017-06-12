package com.spring.project.board.dao;

import java.util.List;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.board.dto.Criteria;
import com.spring.project.board.dto.SearchCriteria;

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
	public List<BoardVO> boardList(SearchCriteria cri) throws Exception;
	
	/**
	 * 글 상세보기
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public BoardVO detail(Integer brdno) throws Exception;
	
	/**
	 * 글 수정
	 * @param board
	 * @throws Exception
	 */
	public void modify(BoardVO board) throws Exception;

	/**
	 * 글 삭제
	 * @param brdno
	 */
	public void remove(Integer brdno) throws Exception;

	/**
	 * 글의 총 개수
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int countPage(SearchCriteria cri) throws Exception;
}
