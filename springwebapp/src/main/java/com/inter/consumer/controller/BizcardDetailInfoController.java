package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inter.consumer.service.BizcardDetailInfoService;

@Controller
@RequestMapping("/consumer")
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
