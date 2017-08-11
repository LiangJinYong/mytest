package com.inter.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.dao.WatermarkDetectDao;
import com.inter.service.LogService;
import com.inter.service.WatermarkDetectService;

@Service
public class WatermarkDetectServiceImpl implements WatermarkDetectService {

	@Autowired
	private WatermarkDetectDao watermarkDetectDao;
	
	@Autowired
	private LogService logService;

	public void setWatermarkDetectDao(WatermarkDetectDao watermarkDetectDao) {
		this.watermarkDetectDao = watermarkDetectDao;
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void watermarkDetect(HttpServletRequest request, HttpServletResponse response) {
		String sequence = request.getParameter("SEQUENCE");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		int generation = Integer.parseInt(request.getParameter("GENERATION"));
		int step = Integer.parseInt(request.getParameter("STEP"));
		
		String token = request.getHeader("token");
		
		int appUserCount = watermarkDetectDao.queryAppUserCount(token);
		
		if (appUserCount > 0) {
			
			int seqOrderCount = watermarkDetectDao.querySeqOrderCount(sequence, generation, step);
					
			if (seqOrderCount > 0) {
				
				logService.consumerWatermarkLog(token, sequence, latitude, longitude);
				
				response.setStatus(200);
			} else {
				logService.consumerFailWatermarkLog(token, sequence, latitude, longitude, generation, step);
				response.setStatus(404);
			}
		} else {
			response.setStatus(403);
		}
	}

}
