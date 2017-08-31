package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.PublishTokenDao;

@Repository
public class PublishTokenDaoImpl implements PublishTokenDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCountByPhoneNumber(String mobilePhoneNumber) {

		String sql = "SELECT count(*) FROM app_user WHERE mobile_phone_number = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { mobilePhoneNumber }, Integer.class);
	}

	public void updateAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token,
			String time) {
		String sql = "UPDATE app_user SET os_type = ?, os_version = ?, device = ?, token = ?, regdate = ? WHERE mobile_phone_number = ?";
		jdbcTemplate.update(sql, new Object[] { osType, osVersion, device, token, time, mobilePhoneNumber });
	}

	public void insertAppUser(String mobilePhoneNumber, String osType, String osVersion, String device, String token,
			String time) {
		String sql = "INSERT INTO app_user (user_key, mobile_phone_number, os_type, os_version, device, token, regdate) VALUES (null, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { mobilePhoneNumber, osType, osVersion, device, token, time });
	}

}
