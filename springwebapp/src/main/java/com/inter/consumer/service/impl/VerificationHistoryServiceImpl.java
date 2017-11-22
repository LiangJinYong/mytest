package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.VerificationHistoryDao;
import com.inter.consumer.service.VerificationHistoryService;
import com.inter.util.GetTimeUtil;

@Service
public class VerificationHistoryServiceImpl implements VerificationHistoryService {

	@Autowired
	private VerificationHistoryDao verificationHistoryDao;
	
	public void setVerificationHistoryDao(VerificationHistoryDao verificationHistoryDao) {
		this.verificationHistoryDao = verificationHistoryDao;
	}

	public String verificationHistory(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Gson gson = new Gson();
		
		String mobilePhoneNumber = param.get("mobilePhoneNumber");
		
		Integer userNo = null;
		if (mobilePhoneNumber != null && !("".equals(mobilePhoneNumber))) {
			userNo = verificationHistoryDao.getUserNoByPhoneNumber(mobilePhoneNumber);
			
			if (userNo != null) {
				param.put("userNo", String.valueOf(userNo));
			}
		}
		
		String time = GetTimeUtil.getTime();
		String ymd = GetTimeUtil.getSpecifiedTimeUnit("yyyyMMdd");
		String year = GetTimeUtil.getSpecifiedTimeUnit("yyyy");
		String month = GetTimeUtil.getSpecifiedTimeUnit("MM");
		String day = GetTimeUtil.getSpecifiedTimeUnit("dd");
		
		param.put("time", time);
		param.put("ymd", ymd);
		param.put("year", year);
		param.put("month", month);
		param.put("day", day);
		
		String isSuccess = param.get("isSuccess");
		
		if ("1".equals(isSuccess)) {
			String sequence = param.get("sequence");
			
			Integer orderNumberBySequence = verificationHistoryDao.getOrderNumberBySequence(sequence);
			
			if (orderNumberBySequence == null) {
				param.put("isTimeout", "0");
				verificationHistoryDao.insertFailLog(param);
				
				result.put("logType", "fail");
			} else {
				param.put("orderNum", orderNumberBySequence.toString());
				verificationHistoryDao.insertSuccessLog(param);
				
				// when real success, insert or update detect count and last location
				verificationHistoryDao.insertExtendedDetailInfo(param);
				
				result.put("logType", "success");
			}
		} else {
			param.put("isTimeout", "1");
			verificationHistoryDao.insertFailLog(param);
			
			result.put("logType", "timeout");
		}
		
		return gson.toJson(result);
	}

}
