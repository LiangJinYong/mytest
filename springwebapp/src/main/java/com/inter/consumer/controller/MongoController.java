package com.inter.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter.consumer.dao.MongoDao;

@Controller
@RequestMapping("/consumer")
public class MongoController {

	@Autowired
	private MongoDao mongoDao;
	
	public void setMongoDao(MongoDao mongoDao) {
		this.mongoDao = mongoDao;
	}

	@RequestMapping("/mongo")
	@ResponseBody
	public String mongo() {
		
		String result = mongoDao.findAll();
		return "Mongo Success " + result;
	}
}
