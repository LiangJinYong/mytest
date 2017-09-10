package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.ConfirmCertificationCodeDao;
import com.inter.consumer.service.ConfirmCertificationCodeService;

@Service
public class ConfirmCertificationCodeServiceImpl implements ConfirmCertificationCodeService {

	@Autowired
	private ConfirmCertificationCodeDao confirmCertificationCodeDao;

	public void setCertificationCodeDao(ConfirmCertificationCodeDao confirmCertificationCodeDao) {
		this.confirmCertificationCodeDao = confirmCertificationCodeDao;
	}

	public String confirmCertificationCode(Map<String, String> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String mobilePhoneNumber = param.get("mobile_phone_number");
		
		String certificationCodeDB = confirmCertificationCodeDao.queryCertificationCode(mobilePhoneNumber);
		
		if (certificationCodeDB != null) {
			String certificationCode = param.get("certification_code").trim();
			
			if (certificationCodeDB.equals(certificationCode)) {
				result.put("result_code", 200);
			} else {
				result.put("result_code", 400);
			}
			
		} else {
			result.put("result_code", 404);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	
}
