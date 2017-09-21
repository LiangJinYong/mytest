package com.inter.enterprise.dao;

import java.util.List;
import java.util.Map;

public interface EnterprisePhysicalDistributionEnterpriseListDao {

	Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param);

	List<Map<String, Object>> queryPhysicalDistributionEnterpriseList(Map<String, String> param);

}
