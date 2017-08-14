package com.inter.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.service.FailLogService;
import com.inter.service.LogService;

@Service
public class FailLogServiceImpl implements FailLogService {

	@Autowired
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Map<String, Object> failLog(HttpServletRequest request) {

		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		int generation = Integer.parseInt(request.getParameter("GENERATION"));
		int step = Integer.parseInt(request.getParameter("STEP"));
		String osType = request.getParameter("os_type");
		String device = request.getParameter("device");

		String token = request.getHeader("token");
		
		logService.consumerFailLog(token, latitude, longitude, generation, step, osType, device);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result_code", 200);
		return result;
	}

}
