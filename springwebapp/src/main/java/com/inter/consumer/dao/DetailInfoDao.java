package com.inter.consumer.dao;

import java.util.Map;

public interface DetailInfoDao {

	int queryAppUserCount(String token);

	Map<String, Object> getDetailInfo(String sequence);

}
