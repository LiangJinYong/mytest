package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.PublishTokenService;

@RestController
public class PublishTokenController {

	@Autowired
	private PublishTokenService publishTokenService;

	public void setPublishTokenService(PublishTokenService publishTokenService) {
		this.publishTokenService = publishTokenService;
	}
	
	@RequestMapping("/publishToken")
	public Map<String, Object> publishToken(HttpServletRequest request) {
		
		Map<String, Object> result = publishTokenService.publishToken(request);
		
		return result;
	}
}
