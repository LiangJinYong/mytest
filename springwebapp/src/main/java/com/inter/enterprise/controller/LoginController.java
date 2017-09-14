package com.inter.enterprise.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.enterprise.service.LoginService;
import com.inter.util.RequestParamUtil;

@Controller
@RequestMapping("/enterprise")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		
		Map<String, String[]> paramMap = request.getParameterMap();

		Map<String, String> param = RequestParamUtil.getParamMap(paramMap);
		
		String result = loginService.login(param);
		return result;
	}
}
