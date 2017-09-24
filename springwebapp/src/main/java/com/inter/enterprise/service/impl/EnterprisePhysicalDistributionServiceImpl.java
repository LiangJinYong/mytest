package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionService;
import com.inter.util.GetTimeUtil;

@Service
public class EnterprisePhysicalDistributionServiceImpl implements EnterprisePhysicalDistributionService {

	@Autowired
	private EnterprisePhysicalDistributionDao enterprisePhysicalDistributionDao;

	public void setEnterprisePhysicalDistributionDao(EnterprisePhysicalDistributionDao enterprisePhysicalDistributionDao) {
		this.enterprisePhysicalDistributionDao = enterprisePhysicalDistributionDao;
	}

	public String physicalDistribution(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionDao.queryAppEnterpriseUserByToken(param);
		
		Gson gson = new Gson();
		
		if (appEnterpriseUser != null) {
			int enterpriseUserKey = (Integer) appEnterpriseUser.get("enterprise_user_key");
			param.put("enterpriseUserKey", String.valueOf(enterpriseUserKey));
			
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				String sequence = param.get("SEQUENCE");
				
				Map<String, String> sequenceMap = enterprisePhysicalDistributionDao.querySequence(sequence);
				
				if (sequenceMap != null) {
					Map<String, String> appPhysicalDistributionTypeMap = enterprisePhysicalDistributionDao.queryAppPhysicalDistributionType(sequence);
					
					if (appPhysicalDistributionTypeMap != null) {
						String typeDb = appPhysicalDistributionTypeMap.get("type");
						String typeParam = param.get("type");
						
						if (typeDb.equals(typeParam)) {
							result.put("result_code", 409);
							return gson.toJson(result);
						}
						
						if ("WH".equals(typeParam) || "RL".equals(typeParam) || "SL".equals(typeParam) || "TB".equals(typeParam)) {
						
							String time = GetTimeUtil.getTime();
							param.put("time", time);
							
							try {
								enterprisePhysicalDistributionDao.insertAppPhysicalDistribution(param);
								result.put("result_code", 200);
							} catch (Exception e) {
								result.put("result_code", 500);
							}
						} else {
							result.put("result_code", 400);
						}
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
		
		return gson.toJson(result);
	}
	
	
}
