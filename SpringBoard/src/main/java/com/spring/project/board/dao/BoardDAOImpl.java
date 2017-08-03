package com.spring.project.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.dto.FileVO;
import com.spring.project.common.util.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlsession;
	
	private static String namespace = "com.spring.project.board";
	
	
	@Transactional
	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		
		// insert 실행 후 AUTO_INCREMENT(글번호 brdno)값이 BoardVO의 변수 brdno에 세팅된다.
		sqlsession.insert(namespace+".insertBoard", boardVO);
		
		// AUTO_INCREMENT(댓글 brdno) 값 전달
		return boardVO.getBrdno();	
	}


	@Override
	public List<BoardVO> selectNoticeList(SearchCriteria cri) throws Exception {
		
		return sqlsession.selectList(namespace+".selectNoticeList", cri);
	}
	
	
	@Override
	public List<BoardVO> selectBoardList(SearchCriteria cri) throws Exception {
		
		return sqlsession.selectList(namespace+".selectBoardList", cri);
	}


	@Override
	public BoardVO selectBoardDetail(Integer brdno) throws Exception {
		
		return sqlsession.selectOne(namespace+".selectBoardDetail", brdno);
	}

	
	@Transactional
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		
		sqlsession.update(namespace+".updateBoard", boardVO);
	}

	
	@Transactional
	@Override
	public void deleteBoard(Integer brdno) throws Exception {
		
		sqlsession.delete(namespace+".deleteBoard", brdno);
	}


	@Override
	public int countPage(SearchCriteria cri) throws Exception {
		
		return sqlsession.selectOne(namespace+".countPage", cri);
	}

	
	@Transactional
	@Override
	public void updateViewCnt(Integer brdno) throws Exception {
		
		sqlsession.update(namespace+".updateViewCnt", brdno);
	}

	
	@Transactional
	@Override
	public void insertFile(Map<String, Object> fileMap) throws Exception {
		
		sqlsession.insert(namespace+".insertFile", fileMap);
	}


	@Override
	public List<FileVO> selectFileList(Integer brdno) throws Exception {
		
		return sqlsession.selectList(namespace+".selectFileList", brdno);
	}

	
	@Transactional
	@Override
	public void deleteFileList(Integer brdno) throws Exception {
		
		sqlsession.update(namespace+".deleteFileList", brdno);
	}

	
	@Transactional
	@Override
	public void updateFile(Map<String, Object> fileMap) throws Exception {
		
		sqlsession.update(namespace+".updateFile", fileMap);
	}


	@Override
	public int selectUserArticleCnt(int userno) throws Exception {

		return sqlsession.selectOne(namespace+".selectUserArticleCnt", userno);
	}


	@Override
	public List<BoardVO> selectUserArticleList(Map<String, Object> paramMap) throws Exception {
		
		return sqlsession.selectList(namespace+".selectUserArticleList", paramMap);
	}

	
	@Transactional
	@Override
	public void deleteUserArticle(List<Integer> brdnoList) throws Exception {
		
		sqlsession.update(namespace+".deleteUserArticle", brdnoList);
	}

	
	@Transactional
	@Override
	public void deleteUserArticleFile(List<Integer> brdnoList) throws Exception {
		
		sqlsession.update(namespace+".deleteUserArticleFile", brdnoList);
	}


}
