package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.enterprise.service.EnterpriseVersionCheckService;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseVersionCheckController {

	@Autowired
	private EnterpriseVersionCheckService enterpriseVersionCheckService;

	public void setEnterpriseVersionCheckService(EnterpriseVersionCheckService enterpriseVersionCheckService) {
		this.enterpriseVersionCheckService = enterpriseVersionCheckService;
	}
	
	@RequestMapping("/versionCheck")
	public Map<String, Object> versionCheck(HttpServletRequest request) {
	
		Map<String, Object> result = enterpriseVersionCheckService.versionCheck(request);
		return result;
	}
}
