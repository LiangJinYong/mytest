package com.inter.enterprise.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.EnterprisePhysicalDistributionWatermarkKeyDao;

@Repository
public class EnterprisePhysicalDistributionWatermarkKeyDaoImpl implements EnterprisePhysicalDistributionWatermarkKeyDao {

	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppEnterpriseUserByToken", param);
	}

	public List<Map<String, String>> querySeqByWatermarkKey(Map<String, String> param) {
		return sqlSessionTemplate.selectList(NAMESPACE + "querySeqByWatermarkKey", param);
	}

	public Map<String, String> queryDistributionType(String sequence) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryDistributionType", sequence);
	}

	public void insertAppPhysicalDistribution(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertAppPhysicalDistribution", param);
	}
}
