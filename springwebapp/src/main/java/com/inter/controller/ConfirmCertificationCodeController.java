package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.ConfirmCertificationCodeService;

@RestController
public class ConfirmCertificationCodeController {
	
	@Autowired
	private ConfirmCertificationCodeService confirmCertificationCodeService;

	public void setCertificationCodeService(ConfirmCertificationCodeService confirmCertificationCodeService) {
		this.confirmCertificationCodeService = confirmCertificationCodeService;
	}
	
	@RequestMapping("/confirmCertificationCode")
	public Map<String, Object> confirmCertificationCode(HttpServletRequest request) {
		Map<String, Object> result = confirmCertificationCodeService.confirmCertificationCode(request);
		
		return result;
	}
	
}
