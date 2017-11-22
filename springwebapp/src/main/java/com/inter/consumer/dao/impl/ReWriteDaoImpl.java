package com.inter.consumer.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ReWriteDao;

@Repository
public class ReWriteDaoImpl implements ReWriteDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate orderSqlSession;

	@Autowired
	@Qualifier("authSqlSession")
	private SqlSessionTemplate authSqlSession;

	public void setOrderSqlSession(SqlSessionTemplate orderSqlSession) {
		this.orderSqlSession = orderSqlSession;
	}

	public void setAuthSqlSession(SqlSessionTemplate authSqlSession) {
		this.authSqlSession = authSqlSession;
	}

	public Integer getOrderNumberBySequence(String sequence) {
		return authSqlSession.selectOne(NAMESPACE + "getOrderNumberBySequence", sequence);
	}

	public String queryHomepageAddr(Integer orderNumber) {
		return orderSqlSession.selectOne(NAMESPACE + "queryHomepageAddr", orderNumber);
	}

}
