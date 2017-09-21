package com.inter.enterprise.dao;

import java.util.List;
import java.util.Map;

public interface EnterprisePhysicalDistributionProductListDao {

	Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param);

	List<Map<String, Object>> queryPhysicalDistributionProductList(Map<String, String> param);

}
