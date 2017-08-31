package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.PhysicalDistributionListDao;
import com.inter.consumer.service.PhysicalDistributionListService;

@Service
public class PhysicalDistributionListServiceImpl implements PhysicalDistributionListService {

	@Autowired
	private PhysicalDistributionListDao physicalDistributionListDao;

	public void setPhysicalDistributionListDao(PhysicalDistributionListDao physicalDistributionListDao) {
		this.physicalDistributionListDao = physicalDistributionListDao;
	}

	public Map<String, Object> physicalDistributionList(HttpServletRequest request) {

		String sequence = request.getParameter("SEQUENCE");
		String token = request.getHeader("token");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		int appUserCount = physicalDistributionListDao.queryAppUserCount(token);
		
		if (appUserCount > 0) {

			List<Map<String, Object>> physicalDistributionInfo = physicalDistributionListDao.queryPhysicalDistributionInfo(sequence);
			
			result.put("result_code", 200);
			result.put("data", physicalDistributionInfo);
		} else {
			result.put("result_code", 403);
		}
		
		return result;
	}
	
	
}
