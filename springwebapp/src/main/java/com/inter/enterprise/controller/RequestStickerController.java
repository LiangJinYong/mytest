package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.enterprise.service.RequestStickerService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/enterprise")
public class RequestStickerController {

	@Autowired
	private RequestStickerService requestStickerService;

	public void setRequestStickerService(RequestStickerService requestStickerService) {
		this.requestStickerService = requestStickerService;
	}
	
	@RequestMapping("/requestSticker")
	@ResponseBody
	public String requestSticker(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = requestStickerService.requestSticker(param);
		return result;
	}
}
