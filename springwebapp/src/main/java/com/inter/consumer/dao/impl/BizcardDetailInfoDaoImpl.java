package com.inter.consumer.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.BizcardDetailInfoDao;

@Repository
public class BizcardDetailInfoDaoImpl implements BizcardDetailInfoDao {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	public int queryAppUserCount(String token) {
//		String sql = "SELECT count(*) FROM app_user WHERE token = ?";
//		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
		return 0;
	}

	public Map<String, Object> queryAppBizcard(String sequence) {
//		String sql = "SELECT company_name, name, phone_number, mobile_phone_number, mail, office_addr, position, image_url, regdate FROM app_bizcard WHERE SEQUENCE = ?";
//		return jdbcTemplate.queryForMap(sql, new Object[] { sequence });
		return null;
	}

}
