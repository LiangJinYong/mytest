package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.SignupSuperuserDao;
import com.inter.enterprise.service.SignupSuperuserService;
import com.inter.util.GetTimeUtil;

@Service
public class SignupSuperuserServiceImpl implements SignupSuperuserService {

	@Autowired
	private SignupSuperuserDao signupSuperuserDao;

	public void setSignupSuperuserDao(SignupSuperuserDao signupSuperuserDao) {
		this.signupSuperuserDao = signupSuperuserDao;
	}

	public String signupSuperuser(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String time = GetTimeUtil.getTime();
		param.put("time", time);
		
		try {
			signupSuperuserDao.insertAppEnterprise(param);
			
			signupSuperuserDao.insertAppEnterpriseUser(param);
			
			result.put("resultCode", 200);
		}catch (Exception e) {
			result.put("resultCode", 500);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
