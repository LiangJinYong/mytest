package com.inter.consumer.dao;

import java.util.Map;

public interface VerificationHistoryDao {

	Integer getOrderNumberBySequence(String sequence);

	Integer getUserNoByPhoneNumber(String mobilePhoneNumber);

	void insertFailLog(Map<String, String> param);

	void insertSuccessLog(Map<String, String> param);

	Map<String, Object> getExtendedDetailInfoBySequence(String sequence);

	void updateExtendedDetailInfo(Map<String, String> param);

	void insertExtendedDetailInfo(Map<String, String> param);

}
