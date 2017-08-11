package com.inter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.DetailInfoService;

@RestController
public class DetailInfoController {

	@Autowired
	private DetailInfoService detailInfoService;

	public void setDetailInfoService(DetailInfoService detailInfoService) {
		this.detailInfoService = detailInfoService;
	}
	
	@RequestMapping("/detailInfo")
	public Map<String, Object> detailInfo(HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<String, Object>(); //.detailInfo(request);
		
		result.put("abc", "你好");
		
		return result;
	}
}
