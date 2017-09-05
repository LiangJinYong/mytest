package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.ProductCheckDao;
import com.inter.consumer.service.ProductCheckService;

@Service
public class ProductCheckServiceImpl implements ProductCheckService {

	@Autowired
	private ProductCheckDao productCheckDao;

	public void setProductCheckDao(ProductCheckDao productCheckDao) {
		this.productCheckDao = productCheckDao;
	}

	public String productCheck(Map<String, String> paramMap) {
		String sequence = paramMap.get("SEQUENCE");
		int count = productCheckDao.productCheck(sequence);
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>(); 
		
		if (count > 0) {
			resultMap.put("result_code", 200);
		} else {
			resultMap.put("result_code", 404);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(resultMap);
		
		return result;
	}
	
}
