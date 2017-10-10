package com.inter.consumer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.PublishTokenDao;
import com.inter.consumer.service.PublishTokenService;
import com.inter.util.GetTimeUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class PublishTokenServiceImpl implements PublishTokenService {

	@Autowired
	private PublishTokenDao publishTokenDao;

	public void setPublishTokenDao(PublishTokenDao publishTokenDao) {
		this.publishTokenDao = publishTokenDao;
	}

	public String publishToken(Map<String, String> param) {

		String mobilePhoneNumber = param.get("mobilePhoneNumber");
		String device = param.get("device");

		String token = createToken(mobilePhoneNumber, device);

		int appUserCount = publishTokenDao.queryAppUserCountByPhoneNumber(mobilePhoneNumber);
		
		String time = GetTimeUtil.getTime();
		param.put("token", token);
		param.put("time", time);

		Map<String, Object> result = new HashMap<String, Object>();

		if (appUserCount > 0) {
			try {
				publishTokenDao.updateAppUser(param);
				
				result.put("token", token);
				result.put("resultCode", 200);
			} catch(Exception e) {
				result.put("resultCode", 500);
			}
		} else {
			try {
				publishTokenDao.insertAppUser(param);
				
				result.put("token", token);
				result.put("resultCode", 200);
			} catch(Exception e) {
				result.put("resultCode", 500);
			}
		}
		
		Gson gson = new Gson();

		return gson.toJson(result);
	}

	private String createToken(String mobilePhoneNumber, String device) {
		long time = new Date().getTime();
		String subject = mobilePhoneNumber + time + device;

		String key = "convertedInterface";

		String token = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();

		return token;
	}
}
