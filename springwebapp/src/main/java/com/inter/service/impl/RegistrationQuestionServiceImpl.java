package com.inter.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.RegistrationQuestionDao;
import com.inter.service.RegistrationQuestionService;

@Service
public class RegistrationQuestionServiceImpl implements RegistrationQuestionService {

	@Autowired
	private RegistrationQuestionDao registrationQuestionDao;

	public void setRegistrationQuestionDao(RegistrationQuestionDao registrationQuestionDao) {
		this.registrationQuestionDao = registrationQuestionDao;
	}

	public Map<String, Object> registrationQuestion(HttpServletRequest request)  {
		
		String characterEncoding = request.getCharacterEncoding();
		
		String content = request.getParameter("content");
		String token = request.getHeader("token");
		
		int appUserCount = registrationQuestionDao.queryAppUserCount(token);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (appUserCount > 0) {
			try {
				int userKey = registrationQuestionDao.queryUserKey(token);
				
				String time = getTime();
				
				try {
					registrationQuestionDao.insertAppQuestion(content, time, userKey);
					
					result.put("result_code", 200);
				} catch (Exception e) {
					result.put("result_code", 500);
				}
			} catch (EmptyResultDataAccessException e) {
				result.put("result_code", 404);
			}
		} else {
			result.put("result_code", 403);
		}
		return result;
	}
	
	private String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}

}
