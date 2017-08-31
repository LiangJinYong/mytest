package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.FailLogService;

@RestController
@RequestMapping("/consumer")
public class FailLogController {

	@Autowired
	private FailLogService failLogService;

	public void setFailLogService(FailLogService failLogService) {
		this.failLogService = failLogService;
	}
	
	@RequestMapping("/failLog")
	public Map<String, Object> failLog(HttpServletRequest request) {
		Map<String, Object> result = failLogService.failLog(request);
		return result;
	}
}
