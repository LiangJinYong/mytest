package com.inter.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.BizcardDetailInfoDao;
import com.inter.service.BizcardDetailInfoService;

@Service
public class BizcardDetailInfoServiceImpl implements BizcardDetailInfoService {

	@Autowired
	private BizcardDetailInfoDao bizcardDetailInfoDao;

	public void setBizcardDetailInfoDao(BizcardDetailInfoDao bizcardDetailInfoDao) {
		this.bizcardDetailInfoDao = bizcardDetailInfoDao;
	}

	public Map<String, Object> bizcardDetailInfo(HttpServletRequest request) {
		
		String sequence = request.getParameter("SEQUENCE");
		
		String token = request.getHeader("token");
		
		int appUserCount = bizcardDetailInfoDao.queryAppUserCount(token);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (appUserCount > 0) {
			
			try {
				result = bizcardDetailInfoDao.queryAppBizcard(sequence);
				result.put("result_code", 200);
			} catch (EmptyResultDataAccessException e) {
				
				result.put("result_code", 404);
			}
		} else {
			result.put("result_code", 403);
		}
		return result;
	}
	
}
