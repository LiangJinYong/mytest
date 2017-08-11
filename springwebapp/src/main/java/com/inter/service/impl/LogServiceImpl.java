package com.inter.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.LogDao;
import com.inter.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public void consumerQrLog(String token, String watermarkKey, String latitude, String longitude) {
		try {
			Map<String, Object> appUser = logDao.queryAppUser(token);

			int userNo = (Integer) appUser.get("user_key");
			String mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
			String osType = (String) appUser.get("os_type");
			String device = (String) appUser.get("device");

			try {
				Map<String, Object> seqAndOrder = logDao.querySeqAndOrderByWatermarkKey(watermarkKey);

				String sequence = (String) seqAndOrder.get("SEQUENCE");
				int generation = (Integer) seqAndOrder.get("GENERATION");
				int step = (Integer) seqAndOrder.get("STEP");

				try {
					Map<String, Object> appVersion = logDao.queryAppVersion(osType);

					int currentVersionCode = (Integer) appVersion.get("current_version_code");

					String time = getTime();

					try {
						logDao.insertConsumerQrLog(userNo, watermarkKey, sequence, longitude, latitude, generation,
								step, mobilePhoneNumber, osType, device, currentVersionCode, time);
					} catch (Exception e) {
						throw new RuntimeException("Insert App Log Failed");
					}
				} catch (EmptyResultDataAccessException e) {
					throw new RuntimeException("Get App Version Failed!");
				}
			} catch (EmptyResultDataAccessException e) {
				throw new RuntimeException("Get Seq And Order Failed!");
			}
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Get App User Failed!");
		}
	}

	private String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}

	public void consumerFailQrLog(String token, String watermarkKey, String latitude, String longitude, int generation,
			int step) {
		try {
			Map<String, Object> appUser = logDao.queryAppUser(token);

			int userNo = (Integer) appUser.get("user_key");
			String mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
			String osType = (String) appUser.get("os_type");
			String device = (String) appUser.get("device");

			try {
				Map<String, Object> appVersion = logDao.queryAppVersion(osType);

				int currentVersionCode = (Integer) appVersion.get("current_version_code");

				String time = getTime();

				try {
					logDao.insertConsumerFailQrLog(userNo, watermarkKey, longitude, latitude, generation, step,
							mobilePhoneNumber, osType, device, currentVersionCode, time);
				} catch (Exception e) {
					throw new RuntimeException("Insert App Log Failed");
				}
			} catch (EmptyResultDataAccessException e) {
				throw new RuntimeException("Get App Version Failed!");
			}

		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Get App User Failed!");
		}

	}

	public void consumerWatermarkLog(String token, String sequence, double latitude, double longitude) {
		try {
			Map<String, Object> appUser = logDao.queryAppUser(token);

			int userNo = (Integer) appUser.get("user_key");
			String mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
			String osType = (String) appUser.get("os_type");
			String device = (String) appUser.get("device");

			try {
				Map<String, Object> seqAndOrder = logDao.querySeqAndOrderBySequence(sequence);

				String watermarkKey = (String) seqAndOrder.get("WATERMARK_KEY");
				int generation = (Integer) seqAndOrder.get("GENERATION");
				int step = (Integer) seqAndOrder.get("STEP");

				try {
					Map<String, Object> appVersion = logDao.queryAppVersion(osType);

					int currentVersionCode = (Integer) appVersion.get("current_version_code");

					String time = getTime();

					try {
						logDao.insertConsumerWatermarkLog(userNo, watermarkKey, sequence, longitude, latitude,
								generation, step, mobilePhoneNumber, osType, device, currentVersionCode, time);
					} catch (Exception e) {
						throw new RuntimeException("Insert Consumer Watermark Log Failed");
					}
				} catch (EmptyResultDataAccessException e) {
					throw new RuntimeException("Get App Version Failed!");
				}

			} catch (EmptyResultDataAccessException e) {
				throw new RuntimeException("Get Seq And Order Failed!");
			}

		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Get App User Failed!");
		}
	}

	public void consumerFailWatermarkLog(String token, String sequence, double latitude, double longitude,
			int generation, int step) {

		try {
			Map<String, Object> appUser = logDao.queryAppUser(token);
			
			int userNo = (Integer) appUser.get("user_key");
			String mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
			String osType = (String) appUser.get("os_type");
			String device = (String) appUser.get("device");
			
			Map<String, Object> appVersion = logDao.queryAppVersion(osType);
			
			int currentVersionCode = (Integer) appVersion.get("current_version_code");
			String time = getTime();
			
			try {
				logDao.insertFailConsumerWatermarkLog(userNo, sequence, longitude, latitude, generation, step, mobilePhoneNumber, osType, device, currentVersionCode, time);
			} catch (Exception e) {
				throw new RuntimeException("Insert Consumer Fail Watermark Log Failed");
			}
			
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("Get App User Failed!");
		}
	}

}
