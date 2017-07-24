package com.spring.project.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.dto.FileVO;
import com.spring.project.common.util.SearchCriteria;

public interface BoardDAO {

	/**
	 * 글 등록
	 * @param board
	 * @throws Exception
	 */
	public int insertBoard(BoardVO boardVO) throws Exception;
	
	
	/**
	 * 글 목록
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
	public BoardVO selectBoardDetail(Integer brdno) throws Exception;
	
	
	/**
	 * 글 수정
	 * @param board
	 * @throws Exception
	 */
	public void updateBoard(BoardVO boardVO) throws Exception;

	
	/**
	 * 글 삭제
	 * @param brdno
	 */
	public void deleteBoard(Integer brdno) throws Exception;

	
	/**
	 * 조회수 증가
	 * @param brdno
	 * @throws Exception
	 */
	public void updateViewCnt(Integer brdno) throws Exception;
	
	
	/**
	 * 글의 총 개수
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	public int countPage(SearchCriteria cri) throws Exception;
	
	
	/**
	 * 파일정보를 저장
	 * @param fileMap 파일정보를 담고있는 Map
	 * @throws Exception
	 */
	public void insertFile(Map<String,Object> fileMap) throws Exception;
	
	
	/**
	 * 해당 글에 첨부된 파일 리스트
	 * @param brdno 글번호
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectFileList(Integer brdno) throws Exception;
	
	
	/**
	 * 해당 글에 첨부된 모든 파일의 file_delat을 Y로 변경한다. (파일삭제)
	 * @param brdno
	 * @throws Exception
	 */
	public void deleteFileList(Integer brdno) throws Exception;
	
	
	/**
	 * 특정 파일의 file_delat 값을 N으로 변경한다.
	 * @param fileMap 파일정보를 담고있는 Map
	 * @throws Exception
	 */
	public void updateFile(Map<String,Object> fileMap) throws Exception;
	
	
	/**
	 * 내가쓴 글의 총 개수
	 * @param userno
	 * @return
	 * @throws Exception
	 */
	public int selectUserArticleCnt(int userno) throws Exception;
	
	
	/**
	 * 내가 쓴 글 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> selectUserArticleList(Map<String, Object> paramMap) throws Exception;
	
	
	/**
	 * 내가 쓴 글 삭제
	 * @param brdnoList 삭제 할 글번호 List
	 * @throws Exception
	 */
	public void deleteUserArticle(List<Integer> brdnoList) throws Exception;
	
	
	/**
	 * 내가 쓴 글 삭제시 해당글의 첨부파일 삭제
	 * @param brdnoList 삭제 할 글번호 List
	 * @throws Exception
	 */
	public void deleteUserArticleFile(List<Integer> brdnoList) throws Exception;
}
