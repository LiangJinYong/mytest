package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.ActivateUserDao;
import com.inter.enterprise.service.ActivateUserService;

@Service
public class ActivateUserServiceImpl implements ActivateUserService {

	@Autowired
	private ActivateUserDao activateUserDao;
	
	public void setActivateUserDao(ActivateUserDao activateUserDao) {
		this.activateUserDao = activateUserDao;
	}

	public String activateUser(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> appEnterpriseUserByToken = activateUserDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUserByToken != null) {
			
			int superuserEnterpriseKey = (Integer) appEnterpriseUserByToken.get("enterprise_key");
			String auth = (String) appEnterpriseUserByToken.get("auth");
			
			int enterpriseUserKey = Integer.parseInt(param.get("enterprise_user_key")); 
			
			if ("AU01".equals(auth)) {
				Map<String, Object> appEnterpriseUserByEnterpriseUserKey = activateUserDao.queryAppEnterpriseUserByEnterpriseUserKey(enterpriseUserKey);
				
				if (appEnterpriseUserByEnterpriseUserKey != null) {
					
					int enterpriseKey = (Integer) appEnterpriseUserByEnterpriseUserKey.get("enterprise_key");
					
					if (superuserEnterpriseKey == enterpriseKey) {
						try {
							activateUserDao.updateUserAuth(param);
							result.put("result_code", 200);
						} catch (Exception e) {
							result.put("result_code", 500);
						}
					} else {
						result.put("result_code", 400);
					}
				} else {
					result.put("result_code", 404);
				}
			} else {
				result.put("result_code", 401);
			}
		} else {
			result.put("result_code", 403);
		}

		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
