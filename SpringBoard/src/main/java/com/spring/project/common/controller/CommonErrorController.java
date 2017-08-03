package com.spring.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 에러 URL을 처리할 CommonController
 * @author adm
 *
 */
@Controller
@RequestMapping("/common/error/*")
public class CommonErrorController {

	private static final Logger logger = LoggerFactory.getLogger(CommonErrorController.class);

	
	/**
	 * throwable에 해당하는 에러페이지를 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 공통 에러 페이지(common/error)
	 */
	@RequestMapping(value = "/throwable")
	public String throwable(HttpServletRequest request, Model model) {
		//logger.info(">>>>>> CommonError : throwable");
		return "common/error";
	}

	
	
	/**
	 * exception에 해당하는 에러페이지를 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 공통 에러 페이지(common/error)
	 */
	@RequestMapping(value = "/exception")
	public String exception(HttpServletRequest request, Model model) {
		//logger.info(">>>>>> CommonError : exception");
		return "common/error";
	}
	
	
	/**
	 * 400번 에러에 해당하는 에러페이지를 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 공통 에러 페이지(common/error)
	 */
	@RequestMapping(value = "/400")
	public String pageError400(HttpServletRequest request, Model model) {
		//logger.info(">>>>>> CommonError : page error code 400");
		return "common/error";
	}

	
	
	/**
	 * 404 에러에 해당하는 에러페이지를 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 404 에러 페이지(common/error404)
	 */
	@RequestMapping(value = "/404")
	public String pageError404(HttpServletRequest request, Model model) {
		//logger.info(">>>>>> CommonError : page error code 404");
		return "common/error404";
	}

	
	
	/**
	 * 500 에러에 해당하는 에러페이지를 보여준다.
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param model {@link Model}
	 * 
	 * @return 500 에러 페이지(common/error500)
	 */
	@RequestMapping(value = "/500")
	public String pageError500(HttpServletRequest request, Model model) {
		//logger.info(">>>>>> CommonError : page error code 500");
		return "common/error500";
	}

}
