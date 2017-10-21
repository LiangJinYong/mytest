package com.inter.consumer.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.RegistrationQuestionDao;

@Repository
public class RegistrationQuestionDaoImpl implements RegistrationQuestionDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Integer queryAppUserNoByToken(String token) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppUserNoByToken", token);
	}

	public void insertAppQuestion(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertAppQuestion", param);
	}

}
