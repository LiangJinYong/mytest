package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.ConfirmCertificationCodeDao;
import com.inter.consumer.service.ConfirmCertificationCodeService;

@Service
public class ConfirmCertificationCodeServiceImpl implements ConfirmCertificationCodeService {

	@Autowired
	private ConfirmCertificationCodeDao confirmCertificationCodeDao;

	public void setCertificationCodeDao(ConfirmCertificationCodeDao confirmCertificationCodeDao) {
		this.confirmCertificationCodeDao = confirmCertificationCodeDao;
	}

	public Map<String, Object> confirmCertificationCode(HttpServletRequest request) {
		
		String mobilePhoneNumber = request.getParameter("mobile_phone_number");
		String certificationCode = request.getParameter("certification_code");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			String certificationCodeDB = confirmCertificationCodeDao.queryCertificationCode(mobilePhoneNumber);
			
			if (certificationCode.equals(certificationCodeDB)) {
				result.put("result_code", 200);
			} else {
				result.put("result_code", 400);
			}
		} catch (DataAccessException e) {
			result.put("result_code", 404);
		}
		
		return result;
	}
	
	
}
