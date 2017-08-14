package com.inter.service;

public interface LogService {

	void consumerQrLog(String token, String watermarkKey, String latitude, String longitude, String osType, String device);

	void consumerFailQrLog(String token, String watermarkKey, String latitude, String longitude, int generation, int step, String osType, String device);

	void consumerWatermarkLog(String token, String sequence, double latitude, double longitude, String osType, String device);

	void consumerFailWatermarkLog(String token, String sequence, double latitude, double longitude, int generation,
			int step, String osType, String device);

	void consumerFailLog(String token, double latitude, double longitude, int generation, int step, String osType,
			String device);

}
