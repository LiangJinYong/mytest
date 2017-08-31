package com.inter.consumer.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.PublishTokenDao;
import com.inter.consumer.service.PublishTokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class PublishTokenServiceImpl implements PublishTokenService {

	@Autowired
	private PublishTokenDao publishTokenDao;

	public void setPublishTokenDao(PublishTokenDao publishTokenDao) {
		this.publishTokenDao = publishTokenDao;
	}

	public Map<String, Object> publishToken(HttpServletRequest request) {

		String mobilePhoneNumber = request.getParameter("mobile_phone_number");
		String osType = request.getParameter("os_type");
		String osVersion = request.getParameter("os_version");
		String device = request.getParameter("device");

		String token = createToken(mobilePhoneNumber, device);

		int appUserCount = publishTokenDao.queryAppUserCountByPhoneNumber(mobilePhoneNumber);
		
		String time = getTime();

		Map<String, Object> result = new HashMap<String, Object>();

		if (appUserCount > 0) {
			try {
				publishTokenDao.updateAppUser(mobilePhoneNumber, osType, osVersion, device, token, time);

				result.put("token", token);
				result.put("result_code", 200);
			} catch (DataAccessException e) {
				result.put("result_code", 500);
			}
		} else {
			try {
				publishTokenDao.insertAppUser(mobilePhoneNumber, osType, osVersion, device, token, time);

				result.put("token", token);
				result.put("result_code", 200);
			} catch (DataAccessException e) {
				result.put("result_code", 500);
			}
		}

		return result;
	}

	private String createToken(String mobilePhoneNumber, String device) {
		long time = new Date().getTime();
		String subject = mobilePhoneNumber + time + device;

		String key = "convertedInterface";

		String token = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();

		return token;
	}
	
	private String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}

}