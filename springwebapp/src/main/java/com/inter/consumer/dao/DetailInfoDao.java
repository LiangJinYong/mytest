package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface DetailInfoDao {

	int queryAppUserCount(String token);

	Map<String, Object> queryDetailInfo(String sequence, String generation, String step);

	List<Map<String, Object>> querySvcCdName(String svcCd2);

}
