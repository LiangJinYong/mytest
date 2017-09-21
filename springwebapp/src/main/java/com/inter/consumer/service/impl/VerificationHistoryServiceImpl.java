package com.inter.consumer.service.impl;

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

	public void verificationHistory(Map<String, String> paramMap) {
		
		Gson gson = new Gson();
		
		String logJson = gson.toJson(paramMap);
		
		verificationHistoryDao.verificationHistory(logJson);
	}

}
