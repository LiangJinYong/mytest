package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.ConfirmMailCertificationCodeDao;
import com.inter.enterprise.service.ConfirmMailCertificationCodeService;

@Service
public class ConfirmMailCertificationCodeServiceImpl implements ConfirmMailCertificationCodeService {

	@Autowired
	private ConfirmMailCertificationCodeDao confirmMailCertificationCodeDao;

	public void setConfirmMailCertificationCodeDao(ConfirmMailCertificationCodeDao confirmMailCertificationCodeDao) {
		this.confirmMailCertificationCodeDao = confirmMailCertificationCodeDao;
	}

	public String confirmMailCertificationCode(Map<String, String> param) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, String> certificationCode = confirmMailCertificationCodeDao.queryCertificationCode(param);
		
		if (certificationCode != null) {
			
			String certificationCodeParam = param.get("certificationCode");
			String certificationCodeDB = certificationCode.get("certification_code");
			if (certificationCodeParam.equals(certificationCodeDB)) {
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
