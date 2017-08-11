package com.inter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.dao.Step1QrDao;
import com.inter.service.LogService;
import com.inter.service.Step1QrService;

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

	public Map<String, Object> step1Qr(HttpServletRequest request, HttpServletResponse response) {

		String watermarkKey = request.getParameter("WATERMARK_KEY");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		// token from header
		String token = request.getHeader("token");
		
		int appUserCount = step1QrDao.queryAppUserCount(token);
		
		if (appUserCount > 0) {
			
			List<Map<String, Object>> reSeqList = step1QrDao.queryReSeq(watermarkKey);
			
			if (reSeqList.size() > 0) {
				// insert qr log
				logService.consumerQrLog(token, watermarkKey, latitude, longitude);
				
				response.setStatus(200);
				
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("SEQUENCE", reSeqList.get(0).get("SEQUENCE"));
				return result;
				
			} else {
				// insert fail qr log
				logService.consumerFailQrLog(token, watermarkKey, latitude, longitude, 1, 1);

				response.setStatus(404);
				return null;
			}
			
		} else {
			response.setStatus(403);
			return null;
		}
	}

}
