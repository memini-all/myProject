package com.spring.project.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.common.util.Criteria;
import com.spring.project.common.util.PageCalculate;
import com.spring.project.reply.dto.ReplyVO;
import com.spring.project.reply.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@Inject
	private ReplyService service;

	// 테스트용
	@RequestMapping(value = "/all/{brdno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("brdno") Integer brdno) {

		ResponseEntity<List<ReplyVO>> entity = null;

		try {
			logger.info(">>>>>>> 댓글 조회작업 ........");
			entity = new ResponseEntity<List<ReplyVO>>(service.listAll(brdno), HttpStatus.OK);

		} catch (Exception e) { 
			e.printStackTrace();
			// 등록작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// URL 경로에 {brdno} 가 있는데 이를 활용하려면 메소드의 파라미터에서
	// @PathVariable("brdno") 추가를 해줘야 한다. 그러면 URL 경로에 글번호가 들어가게된다.
	@RequestMapping(value = "/{brdno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> replyList(@PathVariable("brdno") Integer brdno, @PathVariable("page") Integer page) {

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			logger.info(">>>>>>> 댓글 조회 ........");
			
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
			//map.put("imgPath", profileImgPath);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 조회작업 실패시 BAD_REQUEST 전송
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 데이터 전송방식은 JSON이므로 이를 처리하기 위해 @RequestBody 필요
	// Content-Type:application/Json
	// {"brdno" : "65", "userno" : "1", "rcontent" : "댓글테스트", "rgroup" : "1",
	// "rparent" : "0" }
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> replyRegist(@RequestBody ReplyVO replyVO) {

		ResponseEntity<String> entity = null;
		try {
			logger.info(">>>>>>> 댓글 등록 ........");

			service.insertReply(replyVO);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 등록작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// RequestMethod.PUT , RequestMethod.PATCH 방식은 GET와 POST와 어떻게 다른가?
	// 전체 데이터 수정 - PUT 사용, 일부 데이터 수정 - PATCH 사용
	@RequestMapping(value = "/{repno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> replyModify(@PathVariable("repno") Integer repno, @RequestBody ReplyVO replyVO) {

		ResponseEntity<String> entity = null;
		try {
			logger.info(">>>>>>> 댓글 수정 ........");

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

	// Content-Type:application/x-www-form-urlencoded
	@RequestMapping(value = "/{repno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> replyRemove(@PathVariable("repno") Integer repno) {
		
		ResponseEntity<String> entity = null;
		try {
			logger.info(">>>>>>> 댓글 삭제 ........");
			
			service.deleteReply(repno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// 삭제작업 실패시 에러 메시지 전송
			entity = new ResponseEntity<String>("에러 : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
