package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionSequenceListDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionSequenceListService;

@Service
public class EnterprisePhysicalDistributionSequenceListServiceImpl implements EnterprisePhysicalDistributionSequenceListService {

	@Autowired
	private EnterprisePhysicalDistributionSequenceListDao enterprisePhysicalDistributionSequenceListDao;

	public void setEnterprisePhysicalDistributionSequenceListDao(EnterprisePhysicalDistributionSequenceListDao enterprisePhysicalDistributionSequenceListDao) {
		this.enterprisePhysicalDistributionSequenceListDao = enterprisePhysicalDistributionSequenceListDao;
	}

	public String physicalDistributionSequenceList(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionSequenceListDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			int enterpriseKey = (Integer) appEnterpriseUser.get("enterprise_key");
			int enterpriseUserKey = (Integer) appEnterpriseUser.get("enterprise_user_key");
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				param.put("enterpriseKey", String.valueOf(enterpriseKey));
				param.put("enterpriseUserKey", String.valueOf(enterpriseUserKey));
				param.put("auth", auth);

				List<Map<String, Object>> physicalDistributionSequenceList = enterprisePhysicalDistributionSequenceListDao.queryPhysicalDistributionSequenceList(param);
				
				result.put("data", physicalDistributionSequenceList);
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
