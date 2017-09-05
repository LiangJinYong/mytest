package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.VersionCheckService;

@RestController
@RequestMapping("/consumer")
public class VersionCheckController {

	@Autowired
	private VersionCheckService versionCheckService;

	@RequestMapping("/versionCheck")
	@ResponseBody
	public String versionCheck(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();

		String result = versionCheckService.versionCheck(paramMap);

		return "MySQL Success -> count: " + result;
	}

}
