package com.inter.enterprise.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.EnterpriseVersionCheckDao;

@Repository
public class EnterpriseVersionCheckDaoImpl implements EnterpriseVersionCheckDao {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public int getCount(String abc) {
		String sql = "SELECT count(*) FROM test";
		
		SqlParameterSource paramSource = new MapSqlParameterSource("abc", abc);
		Integer count = template.queryForObject(sql, paramSource, Integer.class);
		return count;
	}

}
