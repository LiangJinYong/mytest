package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.Step2QrDao;
import com.inter.consumer.service.LogService;
import com.inter.consumer.service.Step2QrService;

@Service
public class Step2QrServiceImpl implements Step2QrService {

	@Autowired
	private Step2QrDao step2QrDao;
	
	@Autowired
	private LogService logService;

	public void setStep2QrDao(Step2QrDao step2QrDao) {
		this.step2QrDao = step2QrDao;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Map<String, Object> step2Qr(HttpServletRequest request) {
		String watermarkKey = request.getParameter("WATERMARK_KEY");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		String osType = request.getParameter("os_type");
		String device = request.getParameter("device");

		String token = request.getHeader("token");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Map<String, Object>> bchList = step2QrDao.queryBch(watermarkKey);
		
		if (bchList.size() > 0) {
			logService.consumerQrLog(token, watermarkKey, latitude, longitude, osType, device);
			
			if ("".equals(token)) {
				result.put("result_code", 200);
			} else {
				
				int appUserCount = step2QrDao.queryAppUserCount(token);
				
				if (appUserCount > 0) {
					result.put("result_code", 200);
				} else {
					result.put("result_code", 403);
				}
				
			}
			
			result.put("BCH", bchList.get(0).get("BCH"));
		} else {
			logService.consumerFailQrLog(token, watermarkKey, latitude, longitude, 1, 2, osType, device);
			
			result.put("result_code", 404);
		}
		
		return result;
	}
	
	
}
