package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.WatermarkDetectService;

@RestController
public class WatermarkDetectController {

	@Autowired
	private WatermarkDetectService watermarkDetectService;

	public void setWatermarkDetectService(WatermarkDetectService watermarkDetectService) {
		this.watermarkDetectService = watermarkDetectService;
	}
	
	@RequestMapping("/watermarkDetect")
	public Map<String, Object> watermarkDetect(HttpServletRequest request) {
		
		Map<String, Object> result = watermarkDetectService.watermarkDetect(request);
		
		return result;
	}
}
