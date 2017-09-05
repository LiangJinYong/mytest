package com.inter.consumer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.VerificationHistoryService;

@Controller
@RequestMapping("/consumer")
public class VerificationHistoryController {

	@Autowired
	private VerificationHistoryService verificationHistoryService;

	public void setVerificationHistoryService(VerificationHistoryService verificationHistoryService) {
		this.verificationHistoryService = verificationHistoryService;
	}
	
	@RequestMapping("/verificationHistory")
	@ResponseBody
	public String verificationHistory(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		for(Entry<String, String[]> entry : entrySet) {
			paramMap.put(entry.getKey(), entry.getValue()[0]);
		}
		
		verificationHistoryService.verificationHistory(paramMap);
		
		return "Log Insert OK";
	}
}
