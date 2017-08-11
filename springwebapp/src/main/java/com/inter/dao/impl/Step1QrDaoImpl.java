package com.inter.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.dao.Step1QrDao;

@Repository
public class Step1QrDaoImpl implements Step1QrDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCount(String token) {
		String sql = "SELECT count(*) FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public List<Map<String, Object>> queryReSeq(String watermarkKey) {
		String sql = "SELECT re_seq.SEQUENCE FROM re_seq WHERE WATERMARK_KEY = ?";

		return jdbcTemplate.queryForList(sql, new Object[] { watermarkKey });
	}

}
