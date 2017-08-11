package com.inter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.dao.Step2QrDao;
import com.inter.service.LogService;
import com.inter.service.Step2QrService;

@Service
public class Step2QrServiceImpl implements Step2QrService {

	@Autowired
	private Step2QrDao step2QrDao;
	
	@Autowired
	private LogService logService;

	public void setStep2QrDao(Step2QrDao step2QrDao) {
		this.step2QrDao = step2QrDao;
	}

	public Map<String, Object> step2Qr(HttpServletRequest request, HttpServletResponse response) {
		String watermarkKey = request.getParameter("WATERMARK_KEY");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		// token from header
		String token = request.getHeader("token");
		
		int count = step2QrDao.queryAppUserCount(token);
		
		if (count > 0) {
			
			List<Map<String, Object>> list = step2QrDao.queryBch(watermarkKey);
			
			if (list.size() > 0) {
				// insert qr log
				logService.consumerQrLog(token, watermarkKey, latitude, longitude);
				
				response.setStatus(200);
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("BCH", list.get(0).get("BCH"));
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
