package com.inter.service;

public interface LogService {

	void consumerQrLog(String token, String watermarkKey, String latitude, String longitude);

	void consumerFailQrLog(String token, String watermarkKey, String latitude, String longitude, int generation, int step);

	void consumerWatermarkLog(String token, String sequence, double latitude, double longitude);

	void consumerFailWatermarkLog(String token, String sequence, double latitude, double longitude, int generation,
			int step);

}
