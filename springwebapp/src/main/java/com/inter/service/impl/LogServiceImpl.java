package com.inter.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

	public void consumerQrLog(String token, String watermarkKey, String latitude, String longitude, String osType,
			String device) {

		Map<String, Object> appUser = new HashMap<String, Object>();

		if ("".equals(osType) || "".equals(device)) {
			try {
				appUser = logDao.queryAppUser(token);

				osType = (String) appUser.get("os_type");
				device = (String) appUser.get("device");
			} catch (Exception e) {
				osType = "Android";
				device = "";
			}
		}

		int userNo = -1;
		String mobilePhoneNumber = "";

		try {
			appUser = logDao.queryAppUser(token);

			userNo = (Integer) appUser.get("user_key");
			mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
		} catch (Exception e) {
			System.out.println("Get App User Failed!");
		}

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
					logDao.insertConsumerQrLog(userNo, watermarkKey, sequence, longitude, latitude, generation, step,
							mobilePhoneNumber, osType, device, currentVersionCode, time);
				} catch (Exception e) {
					System.out.println("Insert Consumer Qr Log Failed!");
				}
			} catch (EmptyResultDataAccessException e) {
				System.out.println("Get App Version Failed!");
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Get Seq And Order Failed!");
		}
	}

	private String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}

	public void consumerFailQrLog(String token, String watermarkKey, String latitude, String longitude, int generation,
			int step, String osType, String device) {

		Map<String, Object> appUser = new HashMap<String, Object>();

		if ("".equals(osType) || "".equals(device)) {
			try {
				appUser = logDao.queryAppUser(token);

				osType = (String) appUser.get("os_type");
				device = (String) appUser.get("device");
			} catch (Exception e) {
				osType = "Android";
				device = "";
			}
		}

		int userNo = -1;
		String mobilePhoneNumber = "";

		try {
			appUser = logDao.queryAppUser(token);

			userNo = (Integer) appUser.get("user_key");
			mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
		} catch (Exception e) {
			System.out.println("Get App User Failed!");
		}

		try {
			Map<String, Object> appVersion = logDao.queryAppVersion(osType);

			int currentVersionCode = (Integer) appVersion.get("current_version_code");

			String time = getTime();

			try {
				logDao.insertConsumerFailQrLog(userNo, watermarkKey, longitude, latitude, generation, step,
						mobilePhoneNumber, osType, device, currentVersionCode, time);

			} catch (Exception e) {
				System.out.println("Insert Fail Log Failed!");
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Get App Version Failed!");
		}
	}

	public void consumerWatermarkLog(String token, String sequence, double latitude, double longitude, String osType,
			String device) {
		Map<String, Object> appUser = new HashMap<String, Object>();

		if ("".equals(osType) || "".equals(device)) {
			try {
				appUser = logDao.queryAppUser(token);

				osType = (String) appUser.get("os_type");
				device = (String) appUser.get("device");
			} catch (Exception e) {
				osType = "Android";
				device = "";
			}
		}

		int userNo = -1;
		String mobilePhoneNumber = "";

		try {
			appUser = logDao.queryAppUser(token);

			userNo = (Integer) appUser.get("user_key");
			mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
		} catch (Exception e) {
			System.out.println("Get App User Failed!");
		}

		try {
			Map<String, Object> seqAndOrder = logDao.querySeqAndOrderBySequence(sequence);

			String watermarkKey = (String) seqAndOrder.get("WATERMARK_KEY");
			int generation = (Integer) seqAndOrder.get("GENERATION");
			int step = (Integer) seqAndOrder.get("STEP");
			
			try {
				Map<String, Object> appVersion = logDao.queryAppVersion(osType);
				
				int currentVersionCode = (Integer) appVersion.get("current_version_code");
				
				String time = getTime();
				
				logDao.insertConsumerWatermarkLog(userNo, watermarkKey, sequence, longitude, latitude, generation, step, mobilePhoneNumber, osType, device, currentVersionCode, time);
				
			} catch (EmptyResultDataAccessException e) {
				System.out.println("Get App Version Failed!");
			}
		} catch(EmptyResultDataAccessException e) {
			System.out.println("Get Seq And Order Failed!");
		}
	}

	public void consumerFailWatermarkLog(String token, String sequence, double latitude, double longitude,
			int generation, int step, String osType, String device) {

		Map<String, Object> appUser = new HashMap<String, Object>();
		
		if ("".equals(osType) || "".equals(device)) {
			try {
				appUser = logDao.queryAppUser(token);

				osType = (String) appUser.get("os_type");
				device = (String) appUser.get("device");
			} catch (Exception e) {
				osType = "Android";
				device = "";
			}
		}

		int userNo = -1;
		String mobilePhoneNumber = "";

		try {
			appUser = logDao.queryAppUser(token);

			userNo = (Integer) appUser.get("user_key");
			mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
		} catch (Exception e) {
			System.out.println("Get App User Failed!");
		}
		
		try {
			Map<String, Object> appVersion = logDao.queryAppVersion(osType);
			
			int currentVersionCode = (Integer) appVersion.get("current_version_code");
			
			String time = getTime();
		
			try {
				logDao.insertFailConsumerWatermarkLog(userNo, sequence, longitude, latitude, generation, step, mobilePhoneNumber, osType, device, currentVersionCode, time);
			} catch(Exception e) {
				System.out.println("Insert Fail Consumer Watermark Log Failed!");
			}
		} catch(EmptyResultDataAccessException e) {
			System.out.println("Get App Version Failed!");
		}
	}

	public void consumerFailLog(String token, double latitude, double longitude, int generation, int step,
			String osType, String device) {

		Map<String, Object> appUser = new HashMap<String, Object>();

		if ("".equals(osType) || "".equals(device)) {
			try {
				appUser = logDao.queryAppUser(token);

				osType = (String) appUser.get("os_type");
				device = (String) appUser.get("device");
			} catch (EmptyResultDataAccessException e) {
				osType = "Android";
				device = "";
			}
		}

		int userNo = -1;
		String mobilePhoneNumber = "";

		try {
			appUser = logDao.queryAppUser(token);

			userNo = (Integer) appUser.get("user_key");
			mobilePhoneNumber = (String) appUser.get("mobile_phone_number");
		} catch (Exception e) {
			System.out.println("Get App User Failed!");
		}

		try {
			Map<String, Object> appVersion = logDao.queryAppVersion(osType);

			int currentVersionCode = (Integer) appVersion.get("current_version_code");

			String time = getTime();

			try {
				logDao.insertFailLog(userNo, longitude, latitude, generation, step, mobilePhoneNumber, osType, device,
						currentVersionCode, time);
			} catch (Exception e) {
				System.out.println("Insert Fail Log Failed!");
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println("Get App Version Failed!");
		}
	}

}
