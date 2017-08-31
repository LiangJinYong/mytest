package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.DetailInfoService;

@RestController
@RequestMapping("/consumer")
public class DetailInfoController {

	@Autowired
	private DetailInfoService detailInfoService;

	public void setDetailInfoService(DetailInfoService detailInfoService) {
		this.detailInfoService = detailInfoService;
	}
	
	@RequestMapping("/detailInfo")
	public Map<String, Object> detailInfo(HttpServletRequest request) {
		
		Map<String, Object> result = detailInfoService.detailInfo(request);
		
		return result;
	}
}
