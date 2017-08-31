package com.inter.consumer.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.BizcardDetailInfoDao;
import com.inter.consumer.service.BizcardDetailInfoService;

@Service
public class BizcardDetailInfoServiceImpl implements BizcardDetailInfoService {

	@Autowired
	private BizcardDetailInfoDao bizcardDetailInfoDao;

	public void setBizcardDetailInfoDao(BizcardDetailInfoDao bizcardDetailInfoDao) {
		this.bizcardDetailInfoDao = bizcardDetailInfoDao;
	}

	public Map<String, Object> bizcardDetailInfo(HttpServletRequest request) {

		String sequence = request.getParameter("SEQUENCE");

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			result = bizcardDetailInfoDao.queryAppBizcard(sequence);
			String date = date2Str((Timestamp)result.get("regdate"));
			result.put("regdate", date);
			result.put("result_code", 200);
		} catch (EmptyResultDataAccessException e) {
			result.put("result_code", 404);
		}
		return result;
	}
	
	private String date2Str(Timestamp timestamp) {

		java.util.Date d = timestamp;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(d);
	}

}
