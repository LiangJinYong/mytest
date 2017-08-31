package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.Step1QrService;

@RestController
@RequestMapping("/consumer")
public class Step1QrController {

	@Autowired
	private Step1QrService step1QrService;

	public void setStep1QrService(Step1QrService step1QrService) {
		this.step1QrService = step1QrService;
	}
	
	@RequestMapping("/step1Qr")
	public Map<String, Object> step1Qr(HttpServletRequest request) {
		
		Map<String, Object> result = step1QrService.step1Qr(request);
		
		return result;
	}
	
	
}
