package com.inter.enterprise.dao;

import java.util.List;
import java.util.Map;

public interface EnterprisePhysicalDistributionSequenceListDao {

	Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param);

	List<Map<String, Object>> queryPhysicalDistributionSequenceList(Map<String, String> param);

}
