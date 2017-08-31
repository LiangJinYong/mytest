package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.DetailInfoDao;

@Repository
public class DetailInfoDaoImpl implements DetailInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCount(String token) {
		String sql = "SELECT count(*) FROM app_user WHERE token = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public Map<String, Object> queryDetailInfo(String sequence, String generation, String step) {
		String sql = "SELECT lian_order_info.*, lian_user_info.USER_NAME, lian_biz_info.BIZ_NAME, lian_biz_info.HOMEPAGE_ADDR, lian_biz_info.CORP_EMAIL, lian_biz_info.CORP_PHONE, re_seq.SKU_CODE as SKU_CODE_SEQUENCE FROM re_seq "
				+ "LEFT JOIN lian_order_info ON re_seq.ORDERNUMBER = lian_order_info.ORDER_SEQ "
				+ "LEFT JOIN lian_user_info ON lian_order_info.USER_ID = lian_user_info.USER_ID "
				+ "LEFT JOIN lian_biz_info ON lian_user_info.BIZ_ID = lian_biz_info.BIZ_ID "
				+ "WHERE re_seq.SEQUENCE = ? AND lian_order_info.GENERATION = ? AND lian_order_info.STEP = ?";

		return jdbcTemplate.queryForMap(sql, new Object[] { sequence, generation, step });
	}

	public List<Map<String, Object>> querySvcCdName(String svcCd) {
		String sql = "SELECT * FROM licom_comm_cd_dtl WHERE COMM_CD_VAL = ?";
		return jdbcTemplate.queryForList(sql, new Object[] { svcCd });
	}

}
