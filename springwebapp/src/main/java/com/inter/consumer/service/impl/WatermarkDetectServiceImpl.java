package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.WatermarkDetectDao;
import com.inter.consumer.service.LogService;
import com.inter.consumer.service.WatermarkDetectService;

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

	public Map<String, Object> watermarkDetect(HttpServletRequest request) {
		String sequence = request.getParameter("SEQUENCE");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		int generation = Integer.parseInt(request.getParameter("GENERATION"));
		int step = Integer.parseInt(request.getParameter("STEP"));
		
		String osType = request.getParameter("os_type");
		String device = request.getParameter("device");
		
		String token = request.getHeader("token");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			Map<String, Object> seqOrder = watermarkDetectDao.querySeqOrder(sequence, generation, step);
			
			logService.consumerWatermarkLog(token, sequence, latitude, longitude, osType, device);
			
			int isBizcard = (Integer) seqOrder.get("IS_BIZCARD");
			
			if ("".equals(token)) {
				if (isBizcard != 0) {
					result.put("result_code", 201);
				} else {
					result.put("result_code", 200);
				}
			} else {
				int appUserCount = watermarkDetectDao.queryAppUserCount(token);
				
				if (appUserCount > 0) {
					if (isBizcard != 0) {
						result.put("result_code", 201);
					} else {
						result.put("result_code", 200);
					}
				} else {
					result.put("result_code", 403);
				}
			}
			
		} catch(EmptyResultDataAccessException e) {
			logService.consumerFailWatermarkLog(token, sequence, latitude, longitude, generation, step, osType, device);
			result.put("result_code", 404);
		}
		
		return result;
	}

}
