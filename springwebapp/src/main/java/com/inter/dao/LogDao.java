package com.inter.dao;

import java.util.Map;

public interface LogDao {

	Map<String, Object> queryAppUser(String token);

	Map<String, Object> querySeqAndOrderByWatermarkKey(String watermarkKey);

	Map<String, Object> queryAppVersion(String osType);

	void insertConsumerQrLog(int userNo, String watermarkKey, String sequence, String longitude, String latitude,
			int generation, int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode,
			String time);

	void insertConsumerFailQrLog(int userNo, String watermarkKey, String longitude, String latitude, int generation, int step,
			String mobilePhoneNumber, String osType, String device, int currentVersionCode, String time);

	Map<String, Object> querySeqAndOrderBySequence(String sequence);

	void insertConsumerWatermarkLog(int userNo, String watermarkKey, String sequence, double longitude, double latitude,
			int generation, int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode,
			String time);

	void insertFailConsumerWatermarkLog(int userNo, String sequence, double longitude, double latitude, int generation,
			int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode, String time);

}
