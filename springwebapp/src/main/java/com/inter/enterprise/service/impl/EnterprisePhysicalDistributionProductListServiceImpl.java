package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionProductListDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionProductListService;

@Service
public class EnterprisePhysicalDistributionProductListServiceImpl implements EnterprisePhysicalDistributionProductListService {

	@Autowired
	private EnterprisePhysicalDistributionProductListDao enterprisePhysicalDistributionProductListDao;
	
	public void setEnterprisePhysicalDistributionProductListDao(EnterprisePhysicalDistributionProductListDao enterprisePhysicalDistributionProductListDao) {
		this.enterprisePhysicalDistributionProductListDao = enterprisePhysicalDistributionProductListDao;
	}

	public String physicalDistributionProductList(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionProductListDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			int enterpriseKey = (Integer) appEnterpriseUser.get("enterprise_key");
			int enterpriseUserKey = (Integer) appEnterpriseUser.get("enterprise_user_key");
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				param.put("enterpriseKey", String.valueOf(enterpriseKey));
				param.put("enterpriseUserKey", String.valueOf(enterpriseUserKey));
				param.put("auth", auth);
				
				List<Map<String, Object>> physicalDistributionProductList = enterprisePhysicalDistributionProductListDao.queryPhysicalDistributionProductList(param);
				
				result.put("data", physicalDistributionProductList);
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
