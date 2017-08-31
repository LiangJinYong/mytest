package com.inter.consumer.dao;

public interface PublishTokenDao {

	int queryAppUserCountByPhoneNumber(String mobilePhoneNumber);

	void updateAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token, String time);

	void insertAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token, String time);

}
