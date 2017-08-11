package com.inter.dao;

public interface PublishTokenDao {

	int queryCount(String mobilePhoneNumber);

	void updateAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token);

	void insertAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token);

}
