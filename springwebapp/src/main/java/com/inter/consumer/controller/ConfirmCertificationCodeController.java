package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.ConfirmCertificationCodeService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/consumer")
public class ConfirmCertificationCodeController {
	
	@Autowired
	private ConfirmCertificationCodeService confirmCertificationCodeService;

	public void setCertificationCodeService(ConfirmCertificationCodeService confirmCertificationCodeService) {
		this.confirmCertificationCodeService = confirmCertificationCodeService;
	}
	
	@RequestMapping("/confirmCertificationCode")
	@ResponseBody
	public String confirmCertificationCode(HttpServletRequest request) {
		Map<String, String[]> paramMap = request.getParameterMap();
		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = confirmCertificationCodeService.confirmCertificationCode(param);
		return result;
	}
	
}
