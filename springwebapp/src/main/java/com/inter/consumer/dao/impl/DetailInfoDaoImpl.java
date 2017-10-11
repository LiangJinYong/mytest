package com.inter.consumer.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.DetailInfoDao;

@Repository
public class DetailInfoDaoImpl implements DetailInfoDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public int queryAppUserCount(String token) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryAppUserCount", token);
	}

	public Map<String, Object> getDetailInfo(String sequence) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "getDetailInfo", sequence);
	}

}
