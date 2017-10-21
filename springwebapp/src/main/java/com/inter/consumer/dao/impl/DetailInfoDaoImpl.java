package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.DetailInfoDao;

@Repository
public class DetailInfoDaoImpl implements DetailInfoDao {

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

	public int queryAppUserCount(String token) {
		
		return orderSqlSession.selectOne(NAMESPACE + "queryAppUserCount", token);
	}

	public Map<String, Object> getDetailInfo(Map<String, String> param) {
		return orderSqlSession.selectOne(NAMESPACE + "getDetailInfo", param);
	}

	public List<Map<String, Object>> querySvcCdName(String svcCd) {
		return orderSqlSession.selectList(NAMESPACE +"querySvcCdName", svcCd);
	}

	public Map<String, Object> getExtendedDetailInfoBySequence(Map<String, String> param) {
		return orderSqlSession.selectOne(NAMESPACE + "getExtendedDetailInfoBySequence", param);
	}

	public Map<String, Object> getOrderInfoBySequence(String sequence) {
		return authSqlSession.selectOne(NAMESPACE + "getOrderInfoBySequence", sequence);
	}

}
