package com.inter.consumer.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.PhysicalDistributionListDao;

@Repository
public class PhysicalDistributionListDaoImpl implements PhysicalDistributionListDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int queryAppUserCount(String token) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppUserCount", token);
	}

	public List<Map<String, Object>> queryPhysicalDistributionInfo(String sequence) {
		
		return sqlSessionTemplate.selectList(NAMESPACE + "queryPhysicalDistributionInfo", sequence);
	}

}
