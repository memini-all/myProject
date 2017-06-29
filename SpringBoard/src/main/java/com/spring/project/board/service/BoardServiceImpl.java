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
import com.spring.project.common.util.FileUtils;
import com.spring.project.common.util.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	// common-context.xml에 등록한 파일업로드 경로를 주입
	@Resource(name = "uploadPath")
	private String uploadPath;
	
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

}
