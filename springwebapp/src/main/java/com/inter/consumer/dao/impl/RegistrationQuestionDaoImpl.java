package com.inter.consumer.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.RegistrationQuestionDao;

@Repository
public class RegistrationQuestionDaoImpl implements RegistrationQuestionDao {

//	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryAppUserCount(String token) {
		String sql = "SELECT count(*) FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public int queryUserKey(String token) {
		String sql = "SELECT app_user.user_key FROM app_user WHERE token = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { token }, Integer.class);
	}

	public void insertAppQuestion(String content, String time, int userKey) {
		String sql = "INSERT INTO app_question (question_key, content, registration_time, user_key) VALUES (null, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {content, time, userKey});
	}

}
