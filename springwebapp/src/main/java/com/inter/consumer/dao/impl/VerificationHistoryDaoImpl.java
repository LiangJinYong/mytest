package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.VerificationHistoryDao;
import com.mongodb.DBCollection;

@Repository
public class VerificationHistoryDaoImpl implements VerificationHistoryDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void verificationHistory(String logJson) {
		DBCollection logCollection = mongoTemplate.getCollection("log");

		mongoTemplate.insert(logJson, "log");
	}

}
