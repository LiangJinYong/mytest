package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.BizcardDetailInfoService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/consumer")
public class BizcardDetailInfoController {

	@Autowired
	private BizcardDetailInfoService bizcardDetailInfoService;

	public void setBizcardDetailInfoService(BizcardDetailInfoService bizcardDetailInfoService) {
		this.bizcardDetailInfoService = bizcardDetailInfoService;
	}

	@RequestMapping("/bizcardDetailInfo")
	@ResponseBody
	public String bizcardDetailInfo(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = bizcardDetailInfoService.bizcardDetailInfo(param);
		return result;
	}
}
