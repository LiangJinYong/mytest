package com.inter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void watermarkDetect(HttpServletRequest request, HttpServletResponse response) {
		watermarkDetectService.watermarkDetect(request, response);
	}
}
