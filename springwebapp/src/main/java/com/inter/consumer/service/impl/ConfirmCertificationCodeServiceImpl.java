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
		
		String mobilePhoneNumber = param.get("mobilePhoneNumber");
		
		String certificationCodeDB = confirmCertificationCodeDao.queryCertificationCode(mobilePhoneNumber);
		
		if (certificationCodeDB != null) {
			String certificationCode = param.get("certificationCode").trim();
			
			if (certificationCodeDB.equals(certificationCode)) {
				result.put("resultCode", 200);
			} else {
				result.put("resultCode", 400);
			}
			
		} else {
			result.put("resultCode", 404);
		}
		
		Gson gson = new Gson();
		return gson.toJson(result);
	}
	
	
}
