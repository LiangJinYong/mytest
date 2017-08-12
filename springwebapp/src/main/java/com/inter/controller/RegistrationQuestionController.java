package com.inter.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.service.RegistrationQuestionService;

@RestController
public class RegistrationQuestionController {

	@Autowired
	private RegistrationQuestionService registrationQuestionService;

	public void setRegistrationQuestionService(RegistrationQuestionService registrationQuestionService) {
		registrationQuestionService = registrationQuestionService;
	}
	
	@RequestMapping("/registrationQuestion")
	public Map<String, Object> registrationQuestion(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String characterEncoding = request.getCharacterEncoding();
		Map<String, Object> result = registrationQuestionService.registrationQuestion(request);
		
		return result;
	}
}
