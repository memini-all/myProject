package com.spring.project.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.PageCalculate;
import com.spring.project.reply.dto.ReplyVO;
import com.spring.project.reply.service.ReplyService;


/**
 * 댓글 Controller <br>
 * /replies/* URL을 처리한다.
 * 
 * @author adm
 */
@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private ReplyService service;

	
	
	/**
	 * 해당 게시물에 있는 댓글을 조회한다.
	 * 
	 * @param brdno 글 번호
	 * @param page 페이지
	 * 
	 * @return {@link ResponseEntity} 댓글 목록과 페이지 정보
	 */
	@RequestMapping(value = "/{brdno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("brdno") 
			Integer brdno, @PathVariable("page") Integer page) {

		
		/*
		 * URL 경로에 {brdno} 가 있는데 이를 활용하려면 메소드의 파라미터에서
		 * @PathVariable("brdno") 추가를 해줘야 한다. 그러면 URL 경로에 글번호가 들어가게된다.
		 */
		
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			//logger.info(">>>>>>> 댓글 조회 ........");
			
			int replyCount = service.replyCount(brdno);	// 댓글의 총 개수
			
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageCalculate pCalculate = new PageCalculate();
			pCalculate.setCri(cri);
			pCalculate.setTotalCount(replyCount);
			
			List<ReplyVO> rlist = service.selectReplyList(cri, brdno); 	// 댓글 목록
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("replyList", rlist); 			// 댓글은 list로
			map.put("pageCalculate", pCalculate); 	// 페이지 관련 정보는 PageCalculate 객체로 전달
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 조회작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	


	/**
	 * 내가 작성한 댓글 목록을 가져온다.
	 * 
	 * @param userno 사용자 번호
	 * @param page 페이지
	 * 
	 * @return {@link ResponseEntity} 내가 작성한 댓글 목록
	 */
	@RequestMapping(value = "/user/{userno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> userReplyList(
			@PathVariable("userno") Integer userno, @PathVariable("page") Integer page) {

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			//logger.info(">>>>>>> 작성한 댓글 조회 ........");
			
			int replyCount = service.selectUserReplyCnt(userno); // 작성한 댓글의 총 개수
			
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageCalculate pCalculate = new PageCalculate();
			pCalculate.setCri(cri);
			pCalculate.setTotalCount(replyCount);

			List<ReplyVO> replyList = service.selectUserReplyList(cri, userno);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("replyList", replyList); 		// 댓글은 list로
			map.put("pageCalculate", pCalculate); 	// 페이지 관련 정보는 PageCalculate 객체로 전달
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 조회작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	

	
	/**
	 * 댓글을 등록한다. 
	 * 
	 * @param replyVO {@link ReplyVO} 댓글 정보를 담고있는 VO
	 * 
	 * @return {@link ResponseEntity} 결과 메시지
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> replyRegist(@RequestBody ReplyVO replyVO) {

		/*
		 * 데이터 전송방식은 JSON이므로 이를 처리하기 위해 @RequestBody 필요
		 */
		
		ResponseEntity<String> entity = null;
		try {
			//logger.info(">>>>>>> 댓글 등록 ........");

			service.insertReply(replyVO);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 등록작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	

	/**
	 * 댓글을 수정한다.
	 * 
	 * @param repno 수정할 댓글 번호
	 * @param replyVO {@link ReplyVO} 댓글 정보를 담고있는 VO
	 * 
	 * @return {@link ResponseEntity} 결과 메시지
	 */
	@RequestMapping(value = "/{repno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> replyModify(@PathVariable("repno") Integer repno, @RequestBody ReplyVO replyVO) {

		ResponseEntity<String> entity = null;
		try {
			//logger.info(">>>>>>> 댓글 수정 ........");

			replyVO.setRepno(repno);
			service.updateReply(replyVO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 수정작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}


	
	/**
	 * 댓글을 삭제한다.
	 * 
	 * @param repno 삭제할 댓글 번호
	 * 
	 * @return {@link ResponseEntity} 결과 메시지
	 */
	@RequestMapping(value = "/{repno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> replyRemove(@PathVariable("repno") Integer repno) {
		
		ResponseEntity<String> entity = null;
		try {
			//logger.info(">>>>>>> 댓글 삭제 ........");
			
			service.deleteReply(repno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 삭제작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	


	/**
	 * 내가 작성한 댓글을 삭제한다.
	 * 
	 * @param arrayParams 삭제할 댓글 번호 List
	 * 
	 * @return {@link ResponseEntity} 결과 메시지
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ResponseEntity<String> userReplyDelete(
			@RequestParam(value="delRepnoArr[]") List<Integer> arrayParams) {

		ResponseEntity<String> entity = null;
		
		try {
			//logger.info(">>>>>>> 내가 쓴 댓글 삭제 ........"+arrayParams);
			
			service.deleteUserReply(arrayParams);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 삭제작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}
