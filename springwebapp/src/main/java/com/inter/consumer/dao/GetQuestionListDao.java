package com.inter.consumer.dao;

import java.util.List;
import java.util.Map;

public interface GetQuestionListDao {

	int queryAppUserCount(String token);

	List<Map<String, Object>> queryQuestionAnswerList(String token);


}
