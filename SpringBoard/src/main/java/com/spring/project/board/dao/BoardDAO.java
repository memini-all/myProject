package com.spring.project.board.dao;

import java.util.List;
import java.util.Map;


import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.dto.FileVO;
import com.spring.project.common.util.SearchCriteria;

/**
 * BoardDAO interface
 * @author adm
 *
 */
public interface BoardDAO {

	
	/**
	 * 글을 등록한다.
	 * 
	 * @param boardVO {@link BoardVO} 등록할 글 정보를 담은 VO
	 * 
	 * @return int 등록한 글번호
	 * @throws Exception
	 */
	public int insertBoard(BoardVO boardVO) throws Exception;
	
	
	
	/**
	 * 공지사항 목록을 가져온다.
	 * 
	 * @param cri {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * 
	 * @return List 공지사항 목록
	 * @throws Exception
	 */
	public List<BoardVO> selectNoticeList(SearchCriteria cri) throws Exception;
	
	
	
	/**
	 * 글 목록을 가져온다.
	 * 
	 * @param cri {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * 
	 * @return List 글 목록
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(SearchCriteria cri) throws Exception;

	
	
	/**
	 * 상세보기할 글의 정보를 가져온다.
	 * 
	 * @param brdno 글번호
	 * 
	 * @return {@link BoardVO} 상세보기 할 글의 정보
	 * @throws Exception
	 */
	public BoardVO selectBoardDetail(Integer brdno) throws Exception;
	
	
	
	/**
	 * 글을 수정한다.
	 * 
	 * @param boardVO {@link BoardVO}  수정할 글의 정보를 담은 VO
	 * @throws Exception
	 */
	public void updateBoard(BoardVO boardVO) throws Exception;

	
	
	/**
	 * 글을 삭제한다.
	 * 
	 * @param brdno 삭제할 글 번호
	 * @throws Exception
	 */
	public void deleteBoard(Integer brdno) throws Exception;

	
	
	/**
	 * 조회수를 증가시킨다.
	 * 
	 * @param brdno 조회수를 증가시킬 글의 글번호
	 * @throws Exception
	 */
	public void updateViewCnt(Integer brdno) throws Exception;
	
	
	
	/**
	 * 글의 총 개수를 가져온다.
	 * 
	 * @param cri {@link SearchCriteria} 페이지 및 검색 키워드, 검색타입(제목, 내용 등)을 담은 클래스
	 * 
	 * @return int 글의 총 개수
	 * @throws Exception
	 */
	public int countPage(SearchCriteria cri) throws Exception;
	
	
	
	/**
	 * 파일정보를 저장한다.
	 * 
	 * @param fileMap 파일정보를 담고있는 Map
	 * @throws Exception
	 */
	public void insertFile(Map<String,Object> fileMap) throws Exception;
	
	
	
	/**
	 * 해당 글에 첨부된 파일 목록을 가져온다.
	 * 
	 * @param brdno 글 번호
	 * 
	 * @return List 파일목록
	 * @throws Exception
	 */
	public List<FileVO> selectFileList(Integer brdno) throws Exception;
	
	
	
	/**
	 * 해당 글에 첨부된 모든 파일의 file_delat을 Y로 변경한다. (파일삭제 처리)
	 * 
	 * @param brdno 글 번호
	 * @throws Exception
	 */
	public void deleteFileList(Integer brdno) throws Exception;
	
	
	
	/**
	 * 글 수정시 특정 파일의 file_delat 값을 N으로 변경한다.
	 * 
	 * @param fileMap 파일정보를 담고있는 Map
	 * @throws Exception
	 */
	public void updateFile(Map<String,Object> fileMap) throws Exception;
	
	
	
	/**
	 * 내가쓴 글의 총 개수를 가져온다.
	 * 
	 * @param userno 사용자 번호
	 * 
	 * @return int 작성글 총 개수
	 * @throws Exception
	 */
	public int selectUserArticleCnt(int userno) throws Exception;
	
	
	
	/**
	 * 내가 쓴 글을 조회한다.
	 * 
	 * @param paramMap 사용자 번호, 페이지 정보를 가진 Map
	 * @return List 내가 쓴 글 목록
	 * @throws Exception
	 */
	public List<BoardVO> selectUserArticleList(Map<String, Object> paramMap) throws Exception;
	
	
	
	/**
	 * 내가 쓴 글을 삭제한다.
	 * 
	 * @param brdnoList 삭제 할 글번호 List
	 * @throws Exception
	 */
	public void deleteUserArticle(List<Integer> brdnoList) throws Exception;
	
	
	
	/**
	 * 내가 쓴 글 삭제시 해당글의 첨부파일을 삭제한다.
	 * 
	 * @param brdnoList 삭제 할 글번호 List
	 * @throws Exception
	 */
	public void deleteUserArticleFile(List<Integer> brdnoList) throws Exception;
}
