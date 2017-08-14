package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.Step2QrService;

@RestController
public class Step2QrController {

	@Autowired
	private Step2QrService step2QrService;

	public void setStep2QrService(Step2QrService step2QrService) {
		this.step2QrService = step2QrService;
	}
	
	@RequestMapping("/step2Qr")
	public Map<String, Object> step2Qr(HttpServletRequest request) {
		
		Map<String, Object> result = step2QrService.step2Qr(request);
		
		return result;
	}
}
