package com.inter.consumer.dao;

import java.util.Map;

public interface BizcardDetailInfoDao {

	int queryAppUserCount(String token);

	Map<String, Object> queryAppBizcard(String sequence);

}
