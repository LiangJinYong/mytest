package com.inter.consumer.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.DetailInfoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class DetailInfoDaoImpl implements DetailInfoDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String getDetailInfo(String productKey) {
		DBCollection productCollection = mongoTemplate.getCollection("product_detail");
		
		Set<String> collectionNames = mongoTemplate.getCollectionNames();
		DBObject obj = new BasicDBObject();
		obj.put("PRODUCT_KEY", Double.parseDouble(productKey));
		DBObject dbObject = productCollection.findOne(obj);
		
		String result = dbObject.toString();
		
		return result;
	}

}
