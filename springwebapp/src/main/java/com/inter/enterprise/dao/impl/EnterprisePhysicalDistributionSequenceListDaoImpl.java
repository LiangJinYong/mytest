package com.inter.enterprise.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.EnterprisePhysicalDistributionSequenceListDao;

@Repository
public class EnterprisePhysicalDistributionSequenceListDaoImpl implements EnterprisePhysicalDistributionSequenceListDao {

	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public Map<String, Object> queryAppEnterpriseUserByToken(Map<String, String> param) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppEnterpriseUserByToken", param);
	}

	public List<Map<String, Object>> queryPhysicalDistributionSequenceList(Map<String, String> param) {
		return sqlSessionTemplate.selectList(NAMESPACE + "queryPhysicalDistributionSequenceList", param);
	}

}