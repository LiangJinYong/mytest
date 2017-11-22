package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.EnterprisePhysicalDistributionDao;

@Repository
public class EnterprisePhysicalDistributionDaoImpl implements EnterprisePhysicalDistributionDao {
	
	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate orderSqlSession;
	
	@Autowired
	@Qualifier("authSqlSession")
	private SqlSessionTemplate authSqlSession;


	public Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param) {
		
		return orderSqlSession.selectOne(NAMESPACE + "queryAppEnterpriseUserByToken", param);
	}

	public Map<String, String> queryAppPhysicalDistributionType(String sequence) {
		return orderSqlSession.selectOne(NAMESPACE + "queryAppPhysicalDistributionType", sequence);
	}

	public void insertAppPhysicalDistribution(Map<String, String> param) {
		orderSqlSession.insert(NAMESPACE + "insertAppPhysicalDistribution", param);
	}

	public Map<String, Integer> queryOrderBySequence(String sequence) {
		return authSqlSession.selectOne(NAMESPACE + "queryOrderBySequence", sequence);
	}

	public Map<String, String> queryBizServiceInfo(int orderNumber) {
		return orderSqlSession.selectOne(NAMESPACE + "queryBizServiceInfo", orderNumber);
	}
}
