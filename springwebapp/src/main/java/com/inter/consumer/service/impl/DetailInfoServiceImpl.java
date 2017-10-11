package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.DetailInfoDao;
import com.inter.consumer.service.DetailInfoService;

@Service
public class DetailInfoServiceImpl implements DetailInfoService {

	@Autowired
	private DetailInfoDao detailInfoDao;

	public void setDetailInfoDao(DetailInfoDao detailInfoDao) {
		this.detailInfoDao = detailInfoDao;
	}

	public String detailInfo(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		String token = param.get("token");
		int appUserCount = detailInfoDao.queryAppUserCount(token);
		
		if (appUserCount > 0) {
			String sequence = param.get("sequence");
			
			Map<String, Object> detailInfoMap = detailInfoDao.getDetailInfo(sequence);
			

			if (detailInfoMap != null) {
				
				
				result.put("resultCode", 200);
			} else {
				result.put("resultCode", 404);
			}
		} else {
			result.put("resultCode", 403);
		}
		
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
