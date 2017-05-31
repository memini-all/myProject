package com.spring.project.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.project.board.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlsession;
	
	private static String namespace = "com.spring.project.board";
	
	/**
	 * 글 등록
	 */
	@Override
	public void regist(BoardVO board) throws Exception {
		
		sqlsession.insert(namespace+".regist", board);
	}

}
