package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.SendCertificationCodeDao;

@Repository
public class SendCertificationCodeDaoImpl implements SendCertificationCodeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertCertificationCode(String mobilePhoneNumber, String certificationCode, String time) {
		String sql = "INSERT INTO certification_code (method, certification_code, time) VALUES (?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE certification_code = ?, time = ?, method = ?";

		jdbcTemplate.update(sql, new Object[] { mobilePhoneNumber, certificationCode, time, certificationCode, time,
				mobilePhoneNumber });

	}

}
