package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ConfirmCertificationCodeDao;

@Repository
public class ConfirmCertificationCodeDaoImpl implements ConfirmCertificationCodeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String queryCertificationCode(String mobilePhoneNumber) {
		
		String sql = "SELECT certification_code.certification_code FROM certification_code WHERE method = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {mobilePhoneNumber}, String.class);
	}
	
}
