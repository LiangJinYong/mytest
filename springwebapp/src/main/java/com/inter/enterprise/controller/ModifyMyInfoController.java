package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.enterprise.service.ModifyMyInfoService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/enterprise")
public class ModifyMyInfoController {

	@Autowired
	private ModifyMyInfoService modifyMyInfoService;

	public void setModifyMyInfoService(ModifyMyInfoService modifyMyInfoService) {
		this.modifyMyInfoService = modifyMyInfoService;
	}

	@RequestMapping("/modifyMyInfo")
	@ResponseBody
	public String modifyMyInfo(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);

		String token = request.getHeader("token");
		param.put("token", token);
		
		String result = modifyMyInfoService.modifyMyInfo(param);
		return result;
	}
}
