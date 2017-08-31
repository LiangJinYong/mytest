package com.inter.consumer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.service.ReWriteService;

@Controller
@RequestMapping("/consumer")
public class ReWriteController {

	@Autowired
	private ReWriteService reWriteService;
	
	public void setReWriteService(ReWriteService reWriteService) {
		this.reWriteService = reWriteService;
	}

	@RequestMapping("/reWrite")
	@ResponseBody
	public String reWrite(HttpServletRequest request) {
		
		String result = reWriteService.reWrite(request);
		return result;
	}
}
