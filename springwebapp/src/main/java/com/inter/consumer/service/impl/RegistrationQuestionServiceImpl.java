package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.RegistrationQuestionDao;
import com.inter.consumer.service.RegistrationQuestionService;
import com.inter.util.GetTimeUtil;

@Service
public class RegistrationQuestionServiceImpl implements RegistrationQuestionService {

	@Autowired
	private RegistrationQuestionDao registrationQuestionDao;

	public void setRegistrationQuestionDao(RegistrationQuestionDao registrationQuestionDao) {
		this.registrationQuestionDao = registrationQuestionDao;
	}

	public String registrationQuestion(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		String token = param.get("token");
		
		Integer appUserNo =  registrationQuestionDao.queryAppUserNoByToken(token);
		
		if (appUserNo != null) {
			String time = GetTimeUtil.getTime();
			
			param.put("time", time);
			
			try {
				registrationQuestionDao.insertAppQuestion(param);
				result.put("resultCode", 200);
			} catch (Exception e) {
				result.put("resultCode", 500);
			}
		} else {
			result.put("resultCode", 403);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
