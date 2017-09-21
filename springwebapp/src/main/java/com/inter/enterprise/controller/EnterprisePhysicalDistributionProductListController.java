package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.enterprise.service.EnterprisePhysicalDistributionProductListService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/enterprise")
public class EnterprisePhysicalDistributionProductListController {

	@Autowired
	private EnterprisePhysicalDistributionProductListService enterprisePhysicalDistributionProductListService;

	public void setEnterprisePhysicalDistributionProductListService(EnterprisePhysicalDistributionProductListService enterprisePhysicalDistributionProductListService) {
		this.enterprisePhysicalDistributionProductListService = enterprisePhysicalDistributionProductListService;
	}
	
	@RequestMapping("/physicalDistributionProductList")
	@ResponseBody
	public String physicalDistributionProductList(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String token = request.getHeader("token");
		param.put("token", token);
		
		String result = enterprisePhysicalDistributionProductListService.physicalDistributionProductList(param);
		return result;
	}
}
