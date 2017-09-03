package com.inter.enterprise.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.EnterpriseVersionCheckDao;

@Repository
public class EnterpriseVersionCheckDaoImpl implements EnterpriseVersionCheckDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
