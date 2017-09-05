package com.inter.consumer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.MongoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository
public class MongoDaoImpl implements MongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String findAll() {
		
		DBCollection coll = mongoTemplate.getCollection("lian");
		
		DBObject obj = new BasicDBObject();
		obj.put("val1", 1000);
		
		DBCursor cursor = coll.find(obj);
		
		/*
		DBCollection coll = mongoTemplate.getCollection("test");
		
		DBObject obj = new BasicDBObject();
		obj.put("aaa", "고추기름");
		
		DBCursor cursor = coll.find(obj);
		
		DBObject one = coll.findOne(obj);
		System.out.println(one);
		

		Iterator<DBObject> iterator = cursor.iterator();
		
		while(iterator.hasNext()) {
			DBObject row = iterator.next();
			ObjectId id = (ObjectId) row.get("_id");
			String aaa = (String) row.get("aaa");
			System.out.println(id + "###" + aaa);
			
			String string = row.toString();
			System.out.println(string);
		}
		*/

		int size = cursor.size();
		
		return "size: " + size;
	}
}
