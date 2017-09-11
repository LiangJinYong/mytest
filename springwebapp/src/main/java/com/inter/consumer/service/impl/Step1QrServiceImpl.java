package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.Step1QrDao;
import com.inter.consumer.service.Step1QrService;

@Service
public class Step1QrServiceImpl implements Step1QrService {

	@Autowired
	private Step1QrDao step1QrDao;


	public void setStep1QrDao(Step1QrDao step1QrDao) {
		this.step1QrDao = step1QrDao;
	}

	public String step1Qr(Map<String, String> param) {
		String watermarkKey  = param.get("WATERMARK_KEY");
		
		Map<String, Integer> result = new HashMap<String, Integer>();

		int count = step1QrDao.getCountByWatermarkKey(watermarkKey);
		
		if (count > 0) {
			result.put("result_code", 200);
		} else {
			result.put("result_code", 404);
		}
			
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
