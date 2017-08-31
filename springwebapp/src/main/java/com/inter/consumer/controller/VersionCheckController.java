package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.VersionCheckService;

@RestController
@RequestMapping("/consumer")
public class VersionCheckController {

	@Autowired
	private VersionCheckService versionCheckService;

	@RequestMapping("/versionCheck")
	public Map<String, Object> versionCheck(HttpServletRequest request) {

		Map<String, Object> result = versionCheckService.versionCheck(request);

		return result;
	}

}
