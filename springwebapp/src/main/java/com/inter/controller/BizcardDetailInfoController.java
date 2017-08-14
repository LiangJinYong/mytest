package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.BizcardDetailInfoService;

@RestController
public class BizcardDetailInfoController {

	@Autowired
	private BizcardDetailInfoService bizcardDetailInfoService;

	public void setBizcardDetailInfoService(BizcardDetailInfoService bizcardDetailInfoService) {
		this.bizcardDetailInfoService = bizcardDetailInfoService;
	}
	
	@RequestMapping("/bizcardDetailInfo")
	public Map<String, Object> bizcardDetailInfo(HttpServletRequest request) {
		Map<String, Object> result = bizcardDetailInfoService.bizcardDetailInfo(request);
		return result;
	}
}
