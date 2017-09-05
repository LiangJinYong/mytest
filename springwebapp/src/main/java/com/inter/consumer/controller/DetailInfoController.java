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

import com.inter.consumer.service.DetailInfoService;

@Controller
@RequestMapping("/consumer")
public class DetailInfoController {

	@Autowired
	private DetailInfoService detailInfoService;

	public void setDetailInfoService(DetailInfoService detailInfoService) {
		this.detailInfoService = detailInfoService;
	}
	
	@RequestMapping("/detailInfo")
	@ResponseBody
	public String detailInfo(HttpServletRequest request) {
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, String> paramMap = new HashMap<String, String>();
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		for(Entry<String, String[]> entry : entrySet) {
			paramMap.put(entry.getKey(), entry.getValue()[0]);
		}
		
		String result = detailInfoService.detailInfo(paramMap);
		return result;
	}
}
