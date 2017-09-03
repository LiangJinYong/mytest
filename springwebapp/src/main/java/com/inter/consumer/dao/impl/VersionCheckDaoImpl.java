package com.inter.consumer.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.VersionCheckDao;

@Repository
public class VersionCheckDaoImpl implements VersionCheckDao {

//	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<String, Object> queryAppVersionByOsType(String osType) {
		
		String sql = "SELECT current_version_code, current_version_name, is_forced_update, UPDATE_DTTM FROM app_version WHERE os_type = ? AND version_key = (SELECT MAX(version_key) FROM app_version WHERE os_type = ?)";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[] {osType, osType});
		return map;
	}

	public int queryAppVersionByCode(int currentVersionCode, int currentVersionCodeDb) {
		
		String sql = "SELECT count(*) FROM app_version WHERE is_forced_update = 1 AND current_version_code > ? AND current_version_code <= ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {currentVersionCode, currentVersionCodeDb}, Integer.class);
	}
	
}
