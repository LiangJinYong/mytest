package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.Step1QrDao;
import com.inter.consumer.service.LogService;
import com.inter.consumer.service.Step1QrService;

@Service
public class Step1QrServiceImpl implements Step1QrService {

	@Autowired
	private Step1QrDao step1QrDao;
	
	@Autowired
	private LogService logService;

	public void setStep1QrDao(Step1QrDao step1QrDao) {
		this.step1QrDao = step1QrDao;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Map<String, Object> step1Qr(HttpServletRequest request) {

		String watermarkKey = request.getParameter("WATERMARK_KEY");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		String osType = request.getParameter("os_type");
		String device = request.getParameter("device");

		String token = request.getHeader("token");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Map<String,Object>> reSeqList = step1QrDao.queryReSeq(watermarkKey);
		
		if (reSeqList.size() > 0) {
			
			logService.consumerQrLog(token, watermarkKey, latitude, longitude, osType, device);
			
			if ("".equals(token)) {
				result.put("result_code", 200);
			} else {
				
				int appUserCount = step1QrDao.queryAppUserCount(token);
				
				if (appUserCount > 0) {
					result.put("result_code", 200);
				} else {
					result.put("result_code", 403);
				}
			}
			
			result.put("SEQUENCE", reSeqList.get(0).get("SEQUENCE"));
		} else {
			logService.consumerFailQrLog(token, watermarkKey, latitude, longitude, 1, 1, osType, device);
			result.put("result_code", 404);
		}
		
		return result;
	}

}
