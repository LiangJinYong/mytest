package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.Step1QrDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
public class Step1QrDaoImpl implements Step1QrDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public int getCountByWatermarkKey(String watermarkKey) {
		DBCollection productCollection = mongoTemplate.getCollection("product");
		
		DBObject obj = new BasicDBObject();
		obj.put("PRODUCT_KEY", watermarkKey);
		
		DBCursor cursor = productCollection.find(obj);
		
		return cursor.size();
	}

}
