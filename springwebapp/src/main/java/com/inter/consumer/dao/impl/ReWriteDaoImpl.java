package com.inter.consumer.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ReWriteDao;

@Repository
public class ReWriteDaoImpl implements ReWriteDao {

//	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<String> queryHomepageAddr(String watermarkKey) {
		String sql = "SELECT lian_biz_info.HOMEPAGE_ADDR FROM re_seq "
				+ "LEFT JOIN lian_order_info ON re_seq.ORDERNUMBER = lian_order_info.ORDER_SEQ "
				+ "LEFT JOIN lian_user_info ON lian_order_info.USER_ID = lian_user_info.USER_ID "
				+ "LEFT JOIN lian_biz_info ON lian_user_info.BIZ_ID = lian_biz_info.BIZ_ID "
				+ "WHERE WATERMARK_KEY = ?";
		return jdbcTemplate.queryForList(sql, new Object[] { watermarkKey }, String.class);
	}

}
