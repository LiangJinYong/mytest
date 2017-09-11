package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.BizcardDetailInfoDao;
import com.inter.consumer.service.BizcardDetailInfoService;

@Service
public class BizcardDetailInfoServiceImpl implements BizcardDetailInfoService {

	@Autowired
	private BizcardDetailInfoDao bizcardDetailInfoDao;

	public void setBizcardDetailInfoDao(BizcardDetailInfoDao bizcardDetailInfoDao) {
		this.bizcardDetailInfoDao = bizcardDetailInfoDao;
	}

	public String bizcardDetailInfo(Map<String, String> param) {

		String sequence = param.get("SEQUENCE");

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> appBizcard = bizcardDetailInfoDao.queryAppBizcard(sequence);

		if (appBizcard != null) {
			result.putAll(appBizcard);
			result.put("result_code", 200);
		} else {
			result.put("result_code", 404);
		}

		Gson gson = new Gson();

		return gson.toJson(result);
	}

}
