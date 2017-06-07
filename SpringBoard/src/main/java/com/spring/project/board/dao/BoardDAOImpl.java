package com.spring.project.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.board.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlsession;
	
	private static String namespace = "com.spring.project.board";
	
	
	@Override
	public void regist(BoardVO board) throws Exception {
		
		sqlsession.insert(namespace+".regist", board);
	}

	
	@Override
	public List<BoardVO> boardList() throws Exception {
		
		return sqlsession.selectList(namespace+".list");
	}


	@Override
	public BoardVO detail(Integer brdno) throws Exception {
		
		return sqlsession.selectOne(namespace+".detail", brdno);
	}


	@Override
	public void modify(BoardVO board) throws Exception {
		
		sqlsession.update(namespace+".update", board);
	}


	@Override
	public void remove(Integer brdno) throws Exception {
		
		sqlsession.delete(namespace+".remove", brdno);
	}
	

}
