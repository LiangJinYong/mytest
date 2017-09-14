package com.inter.enterprise.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.LoginDao;
import com.inter.enterprise.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public String login(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> appEnterpriseUser = loginDao.queryAppEnterpriseUserById(param);
		
		if (appEnterpriseUser != null) {
			String passwordParam = param.get("password");
			String passwordDb = (String) appEnterpriseUser.get("password");
			
			if (passwordParam.equals(passwordDb)) {
				String id = param.get("ID");
				String token = createToken(id);
				
				param.put("token", token);
				
				try {
					loginDao.updateToken(param);
					
					result.put("auth", appEnterpriseUser.get("auth"));
					result.put("type", appEnterpriseUser.get("type"));
					result.put("id", appEnterpriseUser.get("id"));
					result.put("name", appEnterpriseUser.get("name"));
					result.put("token", token);
					
					result.put("result_code", 200);
				} catch (Exception e) {
					result.put("result_code", 500);
				}
			} else {
				result.put("result_code", 412);
			}
		} else {
			result.put("result_code", 412);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	private String createToken(String id) {
		long time = new Date().getTime();
		String subject = id + time;

		String key = "convertedInterface";

		String token = Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, key).compact();

		return token;
	}

}
