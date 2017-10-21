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
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppEnterpriseUserByToken", param);
	}

	public Map<String, String> querySequence(String sequence) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "querySequence", sequence);
	}

	public Map<String, String> queryAppPhysicalDistributionType(String sequence) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppPhysicalDistributionType", sequence);
	}

	public void insertAppPhysicalDistribution(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertAppPhysicalDistribution", param);
	}
}
