package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.UserListDao;
import com.inter.enterprise.service.UserListService;

@Service
public class UserListServiceImpl implements UserListService {

	@Autowired
	private UserListDao userListDao;
	
	public void setUserListDao(UserListDao userListDao) {
		this.userListDao = userListDao;
	}

	public String userList(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> appEnterpriseUser = userListDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth)) {
				int enterpriseKey = (Integer) appEnterpriseUser.get("enterprise_key");
				
				List<Map<String, Object>> userList = userListDao.queryUserList(enterpriseKey);
				
				result.put("data", userList);
				result.put("result_code", 200);
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
