package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.enterprise.service.EnterprisePhysicalDistributionEnterpriseListService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/enterprise")
public class EnterprisePhysicalDistributionEnterpriseListController {

	@Autowired
	private EnterprisePhysicalDistributionEnterpriseListService enterprisePhysicalDistributionEnterpriseListService;

	public void setEnterprisePhysicalDistributionEnterpriseListService(EnterprisePhysicalDistributionEnterpriseListService enterprisePhysicalDistributionEnterpriseListService) {
		this.enterprisePhysicalDistributionEnterpriseListService = enterprisePhysicalDistributionEnterpriseListService;
	}
	
	@RequestMapping("/physicalDistributionEnterpriseList")
	@ResponseBody
	public String physicalDistributionEnterpriseList(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String token = request.getHeader("token");
		param.put("token", token);
		
		String result = enterprisePhysicalDistributionEnterpriseListService.physicalDistributionEnterpriseList(param);
		return result;
	}
}
