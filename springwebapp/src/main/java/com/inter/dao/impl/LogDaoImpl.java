package com.inter.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.dao.LogDao;

@Repository
public class LogDaoImpl implements LogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> queryAppUser(String token) {
		String sql = "SELECT * FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForMap(sql, new Object[] { token });
	}

	public Map<String, Object> querySeqAndOrderByWatermarkKey(String watermarkKey) {
		String sql = "SELECT re_seq.SEQUENCE, lian_order_info.GENERATION, lian_order_info.STEP FROM re_seq LEFT JOIN lian_order_info ON re_seq.ORDERNUMBER = lian_order_info.ORDER_SEQ WHERE WATERMARK_KEY = ?";
		return jdbcTemplate.queryForMap(sql, new Object[] { watermarkKey });
	}

	public Map<String, Object> queryAppVersion(String osType) {
		String sql = "SELECT app_version.current_version_code FROM app_version WHERE os_type = ?";
		return jdbcTemplate.queryForMap(sql, new Object[] { osType });
	}

	public void insertConsumerQrLog(int userNo, String watermarkKey, String sequence, String longitude, String latitude,
			int generation, int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode,
			String time) {
		String sql = "INSERT INTO app_log VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true, true)";
		jdbcTemplate.update(sql, new Object[] { userNo, watermarkKey, sequence, longitude, latitude, generation, step,
				mobilePhoneNumber, osType, device, currentVersionCode, time });
	}

	public void insertConsumerFailQrLog(int userNo, String watermarkKey, String longitude, String latitude,
			int generation, int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode,
			String time) {

		String sql = "INSERT INTO app_log VALUES (null, ?, ?, null, ?, ?, ?, ?, ?, ?, ?, ?, ?, true, false)";
		jdbcTemplate.update(sql, new Object[] { userNo, watermarkKey, longitude, latitude, generation, step,
				mobilePhoneNumber, osType, device, currentVersionCode, time });
	}

	public Map<String, Object> querySeqAndOrderBySequence(String sequence) {
		String sql = "SELECT re_seq.WATERMARK_KEY, lian_order_info.GENERATION, lian_order_info.STEP FROM re_seq LEFT JOIN lian_order_info ON re_seq.ORDERNUMBER = lian_order_info.ORDER_SEQ WHERE SEQUENCE = ?";
		return jdbcTemplate.queryForMap(sql, new Object[] { sequence });
	}

	public void insertConsumerWatermarkLog(int userNo, String watermarkKey, String sequence, double longitude,
			double latitude, int generation, int step, String mobilePhoneNumber, String osType, String device,
			int currentVersionCode, String time) {
		String sql = "INSERT INTO app_log VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, true)";
		jdbcTemplate.update(sql, new Object[] { userNo, watermarkKey, sequence, longitude, latitude, generation, step,
				mobilePhoneNumber, osType, device, currentVersionCode, time });
	}

	public void insertFailConsumerWatermarkLog(int userNo, String sequence, double longitude, double latitude,
			int generation, int step, String mobilePhoneNumber, String osType, String device, int currentVersionCode,
			String time) {

		String sql = "INSERT INTO app_log VALUES (null, ?, null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, false)";
		jdbcTemplate.update(sql, new Object[] { userNo, sequence, longitude, latitude, generation, step,
				mobilePhoneNumber, osType, device, currentVersionCode, time });
	}

	public void insertFailLog(int userNo, double longitude, double latitude, int generation, int step,
			String mobilePhoneNumber, String osType, String device, int currentVersionCode, String time) {

		String sql = "INSERT INTO app_log VALUES (null, ?, null, null, ?, ?, ?, ?, ?, ?, ?, ?, ?, false, false)";
		
		jdbcTemplate.update(sql, new Object[] {userNo, longitude, latitude, generation, step, mobilePhoneNumber, osType, device, currentVersionCode, time});
	}

}
