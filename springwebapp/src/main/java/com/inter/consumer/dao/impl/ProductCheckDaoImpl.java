package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ProductCheckDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
public class ProductCheckDaoImpl implements ProductCheckDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public int productCheck(String sequence) {
		DBCollection productCollection = mongoTemplate.getCollection("product");

		DBObject obj = new BasicDBObject();
		obj.put("SEQUENCE", Double.parseDouble(sequence));

		DBObject product = productCollection.findOne(obj);

		if (product != null) {
			return 1;
		} else {
			return 0;
		}

	}

}
