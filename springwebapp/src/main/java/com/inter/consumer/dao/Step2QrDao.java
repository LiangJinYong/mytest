package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface Step2QrDao {

	int queryAppUserCount(String token);

	List<Map<String, Object>> queryBch(String watermarkKey);

}
