package com.inter.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.GetQuestionListService;

@RestController
public class GetQuestionListController {

	@Autowired
	private GetQuestionListService getQuestionListService;

	public void setGetQuestionListService(GetQuestionListService getQuestionListService) {
		this.getQuestionListService = getQuestionListService;
	}
	
	@RequestMapping("/getQuestionList")
	public Map<String, Object> getQuestionList(HttpServletRequest request) {
		Map<String, Object> result = getQuestionListService.getQuestionList(request);
		
		return result;
	}
}
