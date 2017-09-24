package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterprisePhysicalDistributionWatermarkKeyDao;
import com.inter.enterprise.service.EnterprisePhysicalDistributionWatermarkKeyService;
import com.inter.util.GetTimeUtil;

@Service
public class EnterprisePhysicalDistributionWatermarkKeyServiceImpl implements EnterprisePhysicalDistributionWatermarkKeyService {

	@Autowired
	private EnterprisePhysicalDistributionWatermarkKeyDao enterprisePhysicalDistributionWatermarkKeyDao;
	
	public void setEnterprisePhysicalDistributionWatermarkKeyDao(EnterprisePhysicalDistributionWatermarkKeyDao enterprisePhysicalDistributionWatermarkKeyDao) {
		this.enterprisePhysicalDistributionWatermarkKeyDao = enterprisePhysicalDistributionWatermarkKeyDao;
	}

	public String PhysicalDistributionWatermarkKey(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();		
		Gson gson = new Gson();
		
		Map<String, Object> appEnterpriseUser = enterprisePhysicalDistributionWatermarkKeyDao.queryAppEnterpriseUserByToken(param);
		
		if (appEnterpriseUser != null) {
			int enterpriseUserKey = (Integer) appEnterpriseUser.get("enterprise_user_key");
			param.put("enterpriseUserKey", String.valueOf(enterpriseUserKey));
			
			String auth = (String) appEnterpriseUser.get("auth");
			
			if ("AU01".equals(auth) || "AU02".equals(auth)) {
				List<Map<String, String>> seqList = enterprisePhysicalDistributionWatermarkKeyDao.querySeqByWatermarkKey(param);
				
				if (seqList.size() > 0) {
					String sequence = seqList.get(0).get("SEQUENCE");
					param.put("SEQUENCE", sequence);
					
					Map<String, String> distributionType = enterprisePhysicalDistributionWatermarkKeyDao.queryDistributionType(sequence);
					
					String typeParam = param.get("type");
					if (distributionType != null) {
						String typeDB = distributionType.get("type");
						
						if (typeDB.equals(typeParam)) {
							result.put("result_code", 409);
							return gson.toJson(result);
						}
					} 
					
					if ("WH".equals(typeParam) || "RL".equals(typeParam) || "SL".equals(typeParam) || "TB".equals(typeParam)) {
						String time = GetTimeUtil.getTime();
						param.put("time", time);
						
						try {
							enterprisePhysicalDistributionWatermarkKeyDao.insertAppPhysicalDistribution(param);
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
		
		return gson.toJson(result);
	}

}
