package com.spring.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/common/error/*")
public class CommonErrorController {

	private static final Logger logger = LoggerFactory.getLogger(CommonErrorController.class);

	@RequestMapping(value = "/throwable")
	public String throwable(HttpServletRequest request, Model model) {
		logger.info(">>>>>> CommonError : throwable");
		return "common/error";
	}

	@RequestMapping(value = "/exception")
	public String exception(HttpServletRequest request, Model model) {
		logger.info(">>>>>> CommonError : exception");
		return "common/error";
	}
	
	@RequestMapping(value = "/400")
	public String pageError400(HttpServletRequest request, Model model) {
		logger.info(">>>>>> CommonError : page error code 400");
		return "common/error";
	}

	@RequestMapping(value = "/404")
	public String pageError404(HttpServletRequest request, Model model) {
		logger.info(">>>>>> CommonError : page error code 404");
		return "common/error404";
	}

	@RequestMapping(value = "/500")
	public String pageError500(HttpServletRequest request, Model model) {
		logger.info(">>>>>> CommonError : page error code 500");
		return "common/error500";
	}

}
