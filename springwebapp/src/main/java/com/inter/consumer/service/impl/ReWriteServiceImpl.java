package com.inter.consumer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.ReWriteDao;
import com.inter.consumer.service.ReWriteService;

@Service
public class ReWriteServiceImpl implements ReWriteService {

	@Autowired
	private ReWriteDao reWriteDao;

	public void setReWriteDao(ReWriteDao reWriteDao) {
		this.reWriteDao = reWriteDao;
	}

	public String reWrite(Map<String, String> param) {
		String watermarkKey = param.get("watermarkKey");

		List<String> homepageAddr = reWriteDao.queryHomepageAddr(watermarkKey);

		String result = "";

		if (homepageAddr.size() > 0) {
			result = "<meta http-equiv='refresh' content='0; url=" + homepageAddr.get(0) + "'>";
		}

		return result;
	}

}
