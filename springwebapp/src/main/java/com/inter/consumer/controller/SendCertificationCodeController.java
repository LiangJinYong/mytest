package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.SendCertificationCodeService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/consumer")
public class SendCertificationCodeController {

	@Autowired
	private SendCertificationCodeService sendCertificationCodeService;

	public void setSendCertificationCodeService(SendCertificationCodeService sendCertificationCodeService) {
		this.sendCertificationCodeService = sendCertificationCodeService;
	}

	@RequestMapping("/sendCertificationCode")
	@ResponseBody
	public String sendCertificationCode(HttpServletRequest request) {

		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = sendCertificationCodeService.sendCertificationCode(param);
		return result;
	}
}
