package com.inter.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.PublishTokenDao;
import com.inter.service.PublishTokenService;

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
		
		int count = publishTokenDao.queryCount(mobilePhoneNumber);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (count > 0) {
			try {
				publishTokenDao.updateAppUser(mobilePhoneNumber, osType, osVersion, device, token);
				
				result.put("token", token);
				result.put("result_code", 200);
				return result;
			} catch (DataAccessException e) {
				Map<String, Object> errResult = new HashMap<String, Object>();
				errResult.put("result_code", 500);
				return errResult;
			}
		} else { 
			try {
				publishTokenDao.insertAppUser(mobilePhoneNumber, osType, osVersion, device, token);
				
				result.put("token", token);
				result.put("result_code", 200);
				return result;
			}catch (DataAccessException e) {
				Map<String, Object> errResult = new HashMap<String, Object>();
				errResult.put("result_code", 500);
				return errResult;
			}
		}
	}

	private String createToken(String mobilePhoneNumber, String device) {
		long time = new Date().getTime();
		String subject = mobilePhoneNumber + time + device;

		String key = "redefinedInterface";

		String token = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();

		return token;
	}

}
