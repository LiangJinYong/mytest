package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.Step1QrService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/consumer")
public class Step1QrController {

	@Autowired
	private Step1QrService step1QrService;

	public void setStep1QrService(Step1QrService step1QrService) {
		this.step1QrService = step1QrService;
	}

	@RequestMapping("/step1Qr")
	@ResponseBody
	public String step1Qr(HttpServletRequest request) {

		Map<String, String[]> paramMap = request.getParameterMap();

		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = step1QrService.step1Qr(param);
		
		return result;
	}

}
