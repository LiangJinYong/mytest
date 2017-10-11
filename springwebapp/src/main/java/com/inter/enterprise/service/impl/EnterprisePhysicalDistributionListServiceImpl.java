package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionListDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionListService;

@Service
public class EnterprisePhysicalDistributionListServiceImpl implements EnterprisePhysicalDistributionListService {

	@Autowired
	private EnterprisePhysicalDistributionListDao enterprisePhysicalDistributionListDao;
	
	public void setEnterprisePhysicalDistributionListDao(
			EnterprisePhysicalDistributionListDao enterprisePhysicalDistributionListDao) {
		this.enterprisePhysicalDistributionListDao = enterprisePhysicalDistributionListDao;
	}

	public String enterprisePhysicalDistributionList(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionListDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				List<Map<String, Object>> physicalDistributionList = enterprisePhysicalDistributionListDao.queryPhysicalDistributionList(param);
				
				result.put("data", physicalDistributionList);
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

