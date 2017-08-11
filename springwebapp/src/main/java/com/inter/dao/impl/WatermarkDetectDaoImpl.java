package com.inter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.dao.WatermarkDetectDao;

@Repository
public class WatermarkDetectDaoImpl implements WatermarkDetectDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCount(String token) {
		String sql = "SELECT count(*) FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public int querySeqOrderCount(String sequence, int generation, int step) {
		
		String sql = "SELECT count(*) FROM re_seq LEFT JOIN lian_order_info ON re_seq.ORDERNUMBER = lian_order_info.ORDER_SEQ WHERE SEQUENCE = ? AND GENERATION = ? AND STEP = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { sequence, generation, step }, Integer.class);
	}

}
