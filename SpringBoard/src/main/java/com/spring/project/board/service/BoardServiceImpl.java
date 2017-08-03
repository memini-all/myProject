package com.spring.project.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.board.dao.BoardDAO;
import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.dto.FileVO;
import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.FileUtils;
import com.spring.project.common.util.SearchCriteria;

/**
 * BoardService의 구현체<br>
 * 게시물과 관련된 처리를 한다.
 * @author adm
 *
 */
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	

	/**
	 * common-context.xml에 등록한 파일 업로드 경로
	 */
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	/**
	 * 파일업로드를 처리할 Utils 클래스
	 */
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	
	@Transactional
	@Override
	public void insertBoard(BoardVO boardVO, HttpServletRequest request) throws Exception {
	
		int brdno = boardDAO.insertBoard(boardVO);
		
		List<Map<String,Object>> list = fileUtils.uploadFileInfo(brdno, uploadPath, request);
		
		for(int i=0, size=list.size(); i<size; i++){	
			boardDAO.insertFile(list.get(i));
		}
	}

	
	@Override
	public List<BoardVO> selectNoticeList(SearchCriteria cri) throws Exception {
		
		return boardDAO.selectNoticeList(cri);
	}
	
	
	@Override
	public List<BoardVO> selectBoardList(SearchCriteria cri) throws Exception {
		
		return boardDAO.selectBoardList(cri);
	}

	
	@Transactional
	@Override
	public Map<String, Object> selectBoardDetail(Integer brdno) throws Exception {
		
		BoardVO boardVO = boardDAO.selectBoardDetail(brdno);
		List<FileVO> fileList = boardDAO.selectFileList(brdno);
		
		Map<String, Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("boardVO", boardVO);
		resultMap.put("fileList", fileList);
		
		return resultMap;
	}

	
	@Transactional
	@Override
	public void updateBoard(BoardVO boardVO, HttpServletRequest request) throws Exception {
		
		boardDAO.updateBoard(boardVO);
		boardDAO.deleteFileList(boardVO.getBrdno());
		
		List<Map<String,Object>> list = fileUtils.updateFileInfo(boardVO.getBrdno(), uploadPath, request);
		
		Map<String,Object> tempMap = null;
		
		for(int i=0; i < list.size(); i++){
			tempMap = list.get(i);	
			if(tempMap.get("isNew").equals("Y")){
				boardDAO.insertFile(tempMap);
			}
			else{
				boardDAO.updateFile(tempMap);
			}
		}
		
	}

	
	@Transactional
	@Override
	public void deleteBoard(Integer brdno) throws Exception {
		
		boardDAO.deleteBoard(brdno);
		boardDAO.deleteFileList(brdno);
	}

	
	@Override
	public int countPage(SearchCriteria cri) throws Exception {
		
		return boardDAO.countPage(cri);
	}

	
	@Transactional
	@Override
	public void updateViewCnt(Integer brdno) throws Exception {
	
		boardDAO.updateViewCnt(brdno);
	}

	
	@Override
	public int selectUserArticleCnt(int userno) throws Exception {
		
		return boardDAO.selectUserArticleCnt(userno);
	}

	
	@Override
	public List<BoardVO> selectUserArticleList(Criteria cri, int userno) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userno", userno);
		paramMap.put("cri", cri);
		
		return boardDAO.selectUserArticleList(paramMap);
	}

	
	@Transactional
	@Override
	public void deleteUserArticle(List<Integer> brdnoList) throws Exception {
		
		boardDAO.deleteUserArticle(brdnoList);
		boardDAO.deleteUserArticleFile(brdnoList);	// 첨부파일도 삭제
	}



}
