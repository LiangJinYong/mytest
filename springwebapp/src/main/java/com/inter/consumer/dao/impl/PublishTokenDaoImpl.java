package com.inter.consumer.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.PublishTokenDao;

@Repository
public class PublishTokenDaoImpl implements PublishTokenDao {

	private static final String NAMESPACE = "com.inter.consumer.";

	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int queryAppUserCountByPhoneNumber(String mobilePhoneNumber) {

		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppUserCountByPhoneNumber", mobilePhoneNumber);
	}

	public void updateAppUser(Map<String, String> param) {

		sqlSessionTemplate.update(NAMESPACE + "updateAppUser", param);
	}

	public void insertAppUser(Map<String, String> param) {

		sqlSessionTemplate.insert(NAMESPACE + "insertAppUser", param);
	}

}
