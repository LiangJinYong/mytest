package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter.enterprise.service.EnterpriseVersionCheckService;
import com.inter.util.RequestParamUtil;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseVersionCheckController {

	@Autowired
	private EnterpriseVersionCheckService enterpriseVersionCheckService;

	public void setEnterpriseVersionCheckService(EnterpriseVersionCheckService enterpriseVersionCheckService) {
		this.enterpriseVersionCheckService = enterpriseVersionCheckService;
	}

	@RequestMapping("/versionCheck")
	@ResponseBody
	public String versionCheck(HttpServletRequest request) {

		Map<String, String[]> paramMap = request.getParameterMap();

		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = enterpriseVersionCheckService.versionCheck(param);
		
		return result;
	}
}
