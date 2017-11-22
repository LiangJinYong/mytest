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
				String sequence = param.get("sequence");

				Map<String, Integer> orderMap = enterprisePhysicalDistributionDao.queryOrderBySequence(sequence);

				if (orderMap != null) {
					
					int orderNumber = orderMap.get("orderNumber");
					
					Map<String, String> bizServiceInfo = enterprisePhysicalDistributionDao.queryBizServiceInfo(orderNumber);
					
					if (bizServiceInfo != null) {
						param.put("bizNm", bizServiceInfo.get("bizNm"));
						param.put("svcNm", bizServiceInfo.get("svcNm"));
					}
					
					Map<String, String> appPhysicalDistributionTypeMap = enterprisePhysicalDistributionDao.queryAppPhysicalDistributionType(sequence);

					String typeParam = param.get("type");
					
					if (appPhysicalDistributionTypeMap != null) {
						String typeDb = appPhysicalDistributionTypeMap.get("type");

						if (typeDb.equals(typeParam)) {
							result.put("resultCode", 409);
							return gson.toJson(result);
						}
					}

					if ("WH".equals(typeParam) || "RL".equals(typeParam) || "SL".equals(typeParam) || "TB".equals(typeParam)) {

						String time = GetTimeUtil.getTime();
						param.put("time", time);

						try {
							enterprisePhysicalDistributionDao.insertAppPhysicalDistribution(param);
							result.put("resultCode", 200);
						} catch (Exception e) {
							e.printStackTrace();
							result.put("resultCode", 500);
						}
					} else {
						result.put("resultCode", 400);
					}

				} else {
					result.put("resultCode", 404);
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
