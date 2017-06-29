package com.spring.project.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.util.SearchCriteria;

public interface BoardService {
	
	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public void insertBoard(BoardVO boardVO, HttpServletRequest request) throws Exception;
	
	/**
	 * 글 목록 - 페이지 처리
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(SearchCriteria cri) throws Exception;
	
	/**
	 * 글 상세보기
	 * @param brdno
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardDetail(Integer brdno) throws Exception;
	
	/**
	 * 조회수 증가
	 * @param brdno
	 * @throws Exception
	 */
	public void updateViewCnt(Integer brdno) throws Exception;
	
	/**
	 * 글 수정
	 * @param board
	 * @throws Exception
	 */
	public void updateBoard(BoardVO boardVO, HttpServletRequest request) throws Exception;
	
	/**
	 * 글 삭제
	 * @param brdno
	 * @throws Exception
	 */
	public void deleteBoard(Integer brdno) throws Exception;
	
	/**
	 * 글을 총 개수
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int countPage(SearchCriteria cri) throws Exception;
}
