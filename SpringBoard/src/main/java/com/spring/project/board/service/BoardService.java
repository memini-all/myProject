package com.spring.project.board.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.SearchCriteria;

/**
 * BoardService interface
 * @author adm
 *
 */
public interface BoardService {
	
	
	/**
	 * 글을 등록한다.
	 * 
	 * @param boardVO {@link BoardVO} 등록할 글 정보를 담은 VO
	 * @param request {@link HttpServletRequest} 파일을 가져오기 위해 필요
	 * @throws Exception
	 */
	public void insertBoard(BoardVO boardVO, HttpServletRequest request) throws Exception;
	
	
	
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
	 * @return 상세보기 할 글의 정보, 첨부된 파일정보가 담긴 Map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardDetail(Integer brdno) throws Exception;
	
	
	
	/**
	 * 조회수를 증가시킨다.
	 * 
	 * @param brdno 조회수를 증가시킬 글의 글번호
	 * @throws Exception
	 */
	public void updateViewCnt(Integer brdno) throws Exception;
	
	
	
	/**
	 * 글을 수정한다.
	 * 
	 * @param boardVO {@link BoardVO}  수정할 글의 정보를 담은 VO
	 * @param request {@link HttpServletRequest} 파일을 가져오기 위해 필요
	 * @throws Exception
	 */
	public void updateBoard(BoardVO boardVO, HttpServletRequest request) throws Exception;
	
	
	
	/**
	 * 글을 삭제한다.
	 * 
	 * @param brdno 삭제할 글 번호
	 * @throws Exception
	 */
	public void deleteBoard(Integer brdno) throws Exception;
	
	
	
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
	 * @param cri {@link Criteria} 페이지 정보를 담은 클래스
	 * @param userno 사용자 번호
	 * 
	 * @return List 내가 쓴 글 목록
	 * @throws Exception
	 */
	public List<BoardVO> selectUserArticleList(Criteria cri, int userno) throws Exception;
	
	
	
	/**
	 * 내가 쓴 글을 삭제한다.
	 * 
	 * @param brdnoList 삭제 할 글번호 List
	 * @throws Exception
	 */
	public void deleteUserArticle(List<Integer> brdnoList) throws Exception;
	
}
