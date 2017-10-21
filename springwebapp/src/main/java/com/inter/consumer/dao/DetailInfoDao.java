package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface DetailInfoDao {

	int queryAppUserCount(String token);

	Map<String, Object> getDetailInfo(Map<String, String> param);

	List<Map<String, Object>> querySvcCdName(String svcCd);

	Map<String, Object> getExtendedDetailInfoBySequence(Map<String, String> param);

	Map<String, Object> getOrderInfoBySequence(String sequence);


}
