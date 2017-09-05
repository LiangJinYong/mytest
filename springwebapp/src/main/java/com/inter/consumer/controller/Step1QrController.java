package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inter.consumer.service.Step1QrService;

@Controller
@RequestMapping("/consumer")
public class Step1QrController {

	@Autowired
	private Step1QrService step1QrService;

	public void setStep1QrService(Step1QrService step1QrService) {
		this.step1QrService = step1QrService;
	}
	
	@RequestMapping("/step1Qr")
	public String step1Qr(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		String result = step1QrService.step1Qr(paramMap);
		
		return result;
	}
	
	
}
