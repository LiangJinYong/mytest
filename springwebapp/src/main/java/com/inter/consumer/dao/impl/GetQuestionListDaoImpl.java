package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.GetQuestionListDao;

@Repository
public class GetQuestionListDaoImpl implements GetQuestionListDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int queryAppUserCount(String token) {

		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppUserCount", token);
	}

	public List<Map<String, Object>> queryQuestionAnswerList(String token) {

		return sqlSessionTemplate.selectList(NAMESPACE + "queryQuestionAnswerList", token);
	}

}
