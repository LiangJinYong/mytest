package com.inter.dao;

public interface WatermarkDetectDao {

	int queryAppUserCount(String token);

	int querySeqOrderCount(String sequence, int generation, int step);

}
