package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.PublishTokenService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/consumer")
public class PublishTokenController {

	@Autowired
	private PublishTokenService publishTokenService;

	public void setPublishTokenService(PublishTokenService publishTokenService) {
		this.publishTokenService = publishTokenService;
	}

	@RequestMapping("/publishToken")
	@ResponseBody
	public String publishToken(HttpServletRequest request) {

		Map<String, String[]> paramMap = request.getParameterMap();

		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);

		String result = publishTokenService.publishToken(param);

		return result;
	}
}
