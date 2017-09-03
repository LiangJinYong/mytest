package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.Step1QrDao;

@Repository
public class Step1QrDaoImpl implements Step1QrDao {

//	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> queryReSeq(String watermarkKey) {
		String sql = "SELECT re_seq.SEQUENCE FROM re_seq WHERE WATERMARK_KEY = ?";

		return jdbcTemplate.queryForList(sql, new Object[] { watermarkKey });
	}

}
