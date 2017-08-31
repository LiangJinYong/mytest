package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface PhysicalDistributionListDao {

	int queryAppUserCount(String token);

	List<Map<String, Object>> queryPhysicalDistributionInfo(String sequence);

}
