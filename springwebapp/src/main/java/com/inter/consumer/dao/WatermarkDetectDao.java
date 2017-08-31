package com.inter.consumer.dao;

import java.util.Map;

public interface WatermarkDetectDao {

	int queryAppUserCount(String token);

	Map<String, Object> querySeqOrder(String sequence, int generation, int step);

}
