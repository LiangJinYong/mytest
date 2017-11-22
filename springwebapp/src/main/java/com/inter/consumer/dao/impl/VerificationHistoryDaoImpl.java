package com.inter.consumer.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.VerificationHistoryDao;

@Repository
public class VerificationHistoryDaoImpl implements VerificationHistoryDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate orderSqlSessionTemplate;
	
	@Autowired
	@Qualifier("authSqlSession")
	private SqlSessionTemplate authSqlSessionTemplate;
	
	@Autowired
	@Qualifier("logSqlSession")
	private SqlSessionTemplate logSqlSessionTemplate;
	
	public void setOrderSqlSessionTemplate(SqlSessionTemplate orderSqlSessionTemplate) {
		this.orderSqlSessionTemplate = orderSqlSessionTemplate;
	}

	public void setAuthSqlSessionTemplate(SqlSessionTemplate authSqlSessionTemplate) {
		this.authSqlSessionTemplate = authSqlSessionTemplate;
	}

	public void setLogSqlSessionTemplate(SqlSessionTemplate logSqlSessionTemplate) {
		this.logSqlSessionTemplate = logSqlSessionTemplate;
	}

	public Integer getOrderNumberBySequence(String sequence) {
		return authSqlSessionTemplate.selectOne(NAMESPACE + "getOrderNumberBySequence", sequence);
	}
	
	public Integer getUserNoByPhoneNumber(String mobilePhoneNumber) {
		return orderSqlSessionTemplate.selectOne(NAMESPACE + "getUserNoByPhoneNumber", mobilePhoneNumber);
	}

	public void insertFailLog(Map<String, String> param) {
		logSqlSessionTemplate.insert(NAMESPACE + "insertFailLog", param);
	}

	public void insertSuccessLog(Map<String, String> param) {
		logSqlSessionTemplate.insert(NAMESPACE + "insertSuccessLog", param);
	}
	
	public void insertExtendedDetailInfo(Map<String, String> param) {
		orderSqlSessionTemplate.insert(NAMESPACE + "insertExtendedDetailInfo", param);
	}

}
