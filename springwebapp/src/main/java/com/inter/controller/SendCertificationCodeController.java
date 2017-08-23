package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.SendCertificationCodeService;

@RestController
public class SendCertificationCodeController {

	@Autowired
	private SendCertificationCodeService sendCertificationCodeService;

	public void setSendCertificationCodeService(SendCertificationCodeService sendCertificationCodeService) {
		this.sendCertificationCodeService = sendCertificationCodeService;
	}
	
	@RequestMapping("/sendCertificationCode")
	public Map<String, Object> sendCertificationCode(HttpServletRequest request) {
		Map<String, Object> result = sendCertificationCodeService.sendCertificationCode(request);
		return result;
	}
}
