package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionEnterpriseListDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionEnterpriseListService;

@Service
public class EnterprisePhysicalDistributionEnterpriseListServiceImpl implements EnterprisePhysicalDistributionEnterpriseListService {

	@Autowired
	private EnterprisePhysicalDistributionEnterpriseListDao enterprisePhysicalDistributionEnterpriseListDao;

	public void setEnterprisePhysicalDistributionEnterpriseListDao(EnterprisePhysicalDistributionEnterpriseListDao enterprisePhysicalDistributionEnterpriseListDao) {
		this.enterprisePhysicalDistributionEnterpriseListDao = enterprisePhysicalDistributionEnterpriseListDao;
	}

	public String physicalDistributionEnterpriseList(Map<String, String> param) {

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionEnterpriseListDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			int enterpriseKey = (Integer) appEnterpriseUser.get("enterprise_key");
			int enterpriseUserKey = (Integer) appEnterpriseUser.get("enterprise_user_key");
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				param.put("enterpriseKey", String.valueOf(enterpriseKey));
				param.put("enterpriseUserKey", String.valueOf(enterpriseUserKey));
				param.put("auth", auth);
				
				List<Map<String, Object>> physicalDistributionEnterpriseList = enterprisePhysicalDistributionEnterpriseListDao.queryPhysicalDistributionEnterpriseList(param);
				
				result.put("data", physicalDistributionEnterpriseList);
				result.put("resultCode", 200);
			} else {
				result.put("resultCode", 401);			
			}
		} else {
			result.put("resultCode", 403);			
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
