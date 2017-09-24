package com.inter.enterprise.dao;

import java.util.List;
import java.util.Map;

public interface EnterprisePhysicalDistributionWatermarkKeyDao {

	Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param);

	List<Map<String, String>> querySeqByWatermarkKey(Map<String, String> param);

	Map<String, String> queryDistributionType(String sequence);

	void insertAppPhysicalDistribution(Map<String, String> param);

}
