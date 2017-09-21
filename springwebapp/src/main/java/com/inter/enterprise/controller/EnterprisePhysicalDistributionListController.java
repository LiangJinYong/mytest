package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter.enterprise.service.EnterprisePhysicalDistributionListService;
import com.inter.util.RequestParamUtil;

@RestController
@RequestMapping("/enterprise")
public class EnterprisePhysicalDistributionListController {

	@Autowired
	private EnterprisePhysicalDistributionListService enterprisePhysicalDistributionListService;

	public void setEnterprisePhysicalDistributionListService(
			EnterprisePhysicalDistributionListService enterprisePhysicalDistributionListService) {
		this.enterprisePhysicalDistributionListService = enterprisePhysicalDistributionListService;
	}
	
	@RequestMapping("/enterprisePhysicalDistributionList")
	@ResponseBody
	public String enterprisePhysicalDistributionList(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String token = request.getHeader("token");
		param.put("token", token);
		
		String result = enterprisePhysicalDistributionListService.enterprisePhysicalDistributionList(param);
		return result;
	}
}
