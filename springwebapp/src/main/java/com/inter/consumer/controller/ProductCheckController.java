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

import com.inter.consumer.service.ProductCheckService;

@Controller
@RequestMapping("/consumer")
public class ProductCheckController {

	@Autowired
	private ProductCheckService productCheckService;

	public void setProductCheckService(ProductCheckService productCheckService) {
		this.productCheckService = productCheckService;
	}
	
	@RequestMapping("/productCheck")
	@ResponseBody
	public String productCheck(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		for(Entry<String, String[]> entry : entrySet) {
			paramMap.put(entry.getKey(), entry.getValue()[0]);
		}

		String result = productCheckService.productCheck(paramMap);
		return result;
	}
}
