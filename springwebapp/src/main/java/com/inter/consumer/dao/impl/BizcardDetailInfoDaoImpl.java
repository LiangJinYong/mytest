package com.inter.consumer.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.BizcardDetailInfoDao;

@Repository
public class BizcardDetailInfoDaoImpl implements BizcardDetailInfoDao {

	private static final String NAMESPACE = "com.inter.consumer.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Map<String, Object> queryAppBizcard(String sequence) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppBizcard", sequence);
	}

}
