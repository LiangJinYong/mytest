package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.inter.enterprise.dao.SignupSuperuserDao;
import com.inter.enterprise.service.SignupSuperuserService;
import com.inter.util.GetTimeUtil;

@Service
@Transactional
public class SignupSuperuserServiceImpl implements SignupSuperuserService {

	@Autowired
	private SignupSuperuserDao signupSuperuserDao;

	public void setSignupSuperuserDao(SignupSuperuserDao signupSuperuserDao) {
		this.signupSuperuserDao = signupSuperuserDao;
	}

	public String signupSuperuser(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		Gson gson = new Gson();
		
		String currentMail = param.get("mail");
		String currentId = param.get("id");
		
		int currentMailCount = signupSuperuserDao.getCurrentMailCount(currentMail); 
		int currentIdCount = signupSuperuserDao.getCurrentIdCount(currentId); 
		
		if (currentMailCount > 0) {
			result.put("resultCode", 408);
			return gson.toJson(result);
		}
		
		if (currentIdCount > 0) {
			result.put("resultCode", 409);
			return gson.toJson(result);
		}
		
		String time = GetTimeUtil.getTime();
		param.put("time", time);
		
		try {
			signupSuperuserDao.insertAppEnterprise(param);
			
			signupSuperuserDao.insertAppEnterpriseUser(param);
			
			result.put("resultCode", 200);
		}catch (Exception e) {
			result.put("resultCode", 500);
		}
		
		return gson.toJson(result);
	}

}
