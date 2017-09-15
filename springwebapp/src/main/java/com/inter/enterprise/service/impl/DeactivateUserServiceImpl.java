package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.DeactivateUserDao;
import com.inter.enterprise.service.DeactivateUserService;

@Service
public class DeactivateUserServiceImpl implements DeactivateUserService {

	@Autowired
	private DeactivateUserDao deactivateUserDao;

	public void setDeactivateUserDao(DeactivateUserDao deactivateUserDao) {
		this.deactivateUserDao = deactivateUserDao;
	}

	public String deactivateUser(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> appEnterpriseUserByToken = deactivateUserDao.queryAppEnterpriseUserByToken(param);

		if (appEnterpriseUserByToken != null) {

			int superuserEnterpriseKey = (Integer) appEnterpriseUserByToken.get("enterprise_key");
			String auth = (String) appEnterpriseUserByToken.get("auth");

			int enterpriseUserKey = Integer.parseInt(param.get("enterprise_user_key"));

			if ("AU01".equals(auth)) {
				Map<String, Object> appEnterpriseUserByEnterpriseUserKey = deactivateUserDao
						.queryAppEnterpriseUserByEnterpriseUserKey(enterpriseUserKey);

				if (appEnterpriseUserByEnterpriseUserKey != null) {

					int enterpriseKey = (Integer) appEnterpriseUserByEnterpriseUserKey.get("enterprise_key");

					if (superuserEnterpriseKey == enterpriseKey) {
						try {
							deactivateUserDao.updateUserAuthDeactivate(param);
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
