package com.inter.consumer.dao;

import java.util.Map;

public interface PublishTokenDao {

	void updateAppUser(Map<String, String> param);

	void insertAppUser(Map<String, String> param);

	int queryAppUserCountByPhoneNumber(String mobilePhoneNumber);

}
