package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.Step1QrDao;
import com.inter.consumer.service.LogService;
import com.inter.consumer.service.Step1QrService;

@Service
public class Step1QrServiceImpl implements Step1QrService {

	@Autowired
	private Step1QrDao step1QrDao;

	@Autowired
	private LogService logService;

	public void setStep1QrDao(Step1QrDao step1QrDao) {
		this.step1QrDao = step1QrDao;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public Map<String, Object> step1Qr(HttpServletRequest request) {

		String watermarkKey = request.getParameter("WATERMARK_KEY");

		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String, Object>> reSeqList = step1QrDao.queryReSeq(watermarkKey);

		if (reSeqList.size() > 0) {
			result.put("result_code", 200);
		} else {
			result.put("result_code", 404);
		}

		return result;
	}

}
