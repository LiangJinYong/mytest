package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface Step1QrDao {

	List<Map<String, Object>> queryReSeq(String watermarkKey);

}
