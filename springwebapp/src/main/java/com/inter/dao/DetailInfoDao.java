package com.inter.dao;

import java.util.Map;

public interface DetailInfoDao {

	int queryAppUserCount(String token);

	Map<String, Object> queryDetailInfo(String sequence, String generation, String step);

}