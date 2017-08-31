package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.PhysicalDistributionListDao;

@Repository
public class PhysicalDistributionListDaoImpl implements PhysicalDistributionListDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCount(String token) {
		String sql = "SELECT count(*) FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public List<Map<String, Object>> queryPhysicalDistributionInfo(String sequence) {
		String sql = "SELECT app_enterprise_user.name AS enterprise_user_name, app_enterprise.name AS enterprise_name, app_enterprise.type AS enterprise_type, "
				+ "app_physical_distribution.latitude, app_physical_distribution.longitude, app_physical_distribution.time, app_physical_distribution.type AS product_distribution_type "
				+ "FROM app_physical_distribution LEFT JOIN app_enterprise_user ON app_enterprise_user.enterprise_user_key = app_physical_distribution.enterprise_user_key "
				+ "LEFT JOIN app_enterprise ON app_enterprise_user.enterprise_key = app_enterprise.enterprise_key "
				+ "WHERE app_physical_distribution.SEQUENCE = ? "
				+ "ORDER BY app_physical_distribution.time DESC";
		return jdbcTemplate.queryForList(sql, new Object[] { sequence });
	}

}
