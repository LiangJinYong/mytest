package com.inter.dao;

import java.util.List;
import java.util.Map;

public interface Step1QrDao {

	int queryAppUserCount(String token);

	List<Map<String, Object>> queryReSeq(String watermarkKey);

}
