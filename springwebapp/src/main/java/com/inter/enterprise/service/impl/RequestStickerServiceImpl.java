package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.RequestStickerDao;
import com.inter.enterprise.service.RequestStickerService;
import com.inter.util.GetTimeUtil;

@Service
public class RequestStickerServiceImpl implements RequestStickerService {

	@Autowired
	private RequestStickerDao requestStickerDao;
	
	public void setRequestStickerDao(RequestStickerDao requestStickerDao) {
		this.requestStickerDao = requestStickerDao;
	}

	public String requestSticker(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String time = GetTimeUtil.getTime();
		param.put("time", time);
		
		try {
			requestStickerDao.insertRequestSticker(param);
			result.put("result_code", 200);
		} catch (Exception e) {
			result.put("result_code", 500);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
