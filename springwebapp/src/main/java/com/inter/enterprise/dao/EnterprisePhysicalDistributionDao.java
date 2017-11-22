package com.inter.enterprise.dao;

import java.util.Map;

public interface EnterprisePhysicalDistributionDao {

	Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param);

	Map<String, String> queryAppPhysicalDistributionType(String sequence);

	void insertAppPhysicalDistribution(Map<String, String> param);

	Map<String, Integer> queryOrderBySequence(String sequence);

	Map<String, String> queryBizServiceInfo(int orderNumber);

}
