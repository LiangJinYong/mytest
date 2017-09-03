package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.GetQuestionListDao;

@Repository
public class GetQuestionListDaoImpl implements GetQuestionListDao {

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

	public List<Map<String, Object>> queryQuestionAnswerList(String token) {
//		String sql = "SELECT app_question.*, app_answer.answer_key, app_answer.content AS a_content, app_answer.registration_time AS a_registration_time, app_answer.question_key AS a_question_key "
//				+ "FROM app_question LEFT JOIN app_answer ON app_question.question_key = app_answer.question_key "
//				+ "WHERE user_key = (SELECT user_key FROM app_user WHERE token = ?) "
//				+ "ORDER BY app_question.question_key, registration_time DESC";
//		return jdbcTemplate.queryForList(sql, new Object[] { token });
		return null;
	}

}
