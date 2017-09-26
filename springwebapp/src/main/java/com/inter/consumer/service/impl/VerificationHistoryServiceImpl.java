package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.VerificationHistoryDao;
import com.inter.consumer.service.VerificationHistoryService;

@Service
public class VerificationHistoryServiceImpl implements VerificationHistoryService {

	@Autowired
	private VerificationHistoryDao verificationHistoryDao;
	
	public void setVerificationHistoryDao(VerificationHistoryDao verificationHistoryDao) {
		this.verificationHistoryDao = verificationHistoryDao;
	}

	public String verificationHistory(Map<String, String> param) {
			
		Map<String, Object> result = new HashMap<String, Object>();
		
		String history = param.get("history");
		
		try {
			verificationHistoryDao.verificationHistory(history);
			result.put("result_code", 200);
		} catch (Exception e) {
			result.put("result_code", 404);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
