package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.CreateUserDao;
import com.inter.enterprise.service.CreateUserService;
import com.inter.util.GetTimeUtil;

@Service
public class CreateUserServiceImpl implements CreateUserService {

	@Autowired
	private CreateUserDao createUserDao;

	public void setCreateUserDao(CreateUserDao createUserDao) {
		this.createUserDao = createUserDao;
	}

	public String createUser(Map<String, String> param) {

		Map<String, Object> result = new HashMap<String, Object>();
		Gson gson = new Gson();
		
		String currentId = param.get("id");
		int currentIdCount = createUserDao.getCurrentIdCount(currentId);
		
		if (currentIdCount > 0) {
			result.put("resultCode", 409);
			return gson.toJson(result);
		}
		
		Map<String, Object> appEnterpriseUser = createUserDao.queryAppEnterpriseUserByToken(param);

		if (appEnterpriseUser != null) {

			String auth = (String) appEnterpriseUser.get("auth");

			if ("AU01".equals(auth)) {
				int enterpriseKey = (Integer) appEnterpriseUser.get("enterprise_key");

				String time = GetTimeUtil.getTime();
				param.put("time", time);
				param.put("enterpriseKey", String.valueOf(enterpriseKey));

				try {
					createUserDao.insertNormalAppEnterpriseUser(param);
					result.put("resultCode", 200);
				} catch (Exception e) {
					result.put("resultCode", 500);
				}
			} else {
				result.put("resultCode", 401);
			}

		} else {
			result.put("resultCode", 403);
		}
		
		return gson.toJson(result);
	}

}
