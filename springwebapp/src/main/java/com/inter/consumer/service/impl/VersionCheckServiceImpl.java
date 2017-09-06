package com.inter.consumer.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.VersionCheckDao;
import com.inter.consumer.service.VersionCheckService;

@Service
public class VersionCheckServiceImpl implements VersionCheckService {

	@Autowired
	private VersionCheckDao versionCheckDao;
	
	public void setVersionCheckDao(VersionCheckDao versionCheckDao) {
		this.versionCheckDao = versionCheckDao;
	}

	public String versionCheck(Map<String, String> paramMap) {
		String osType = paramMap.get("os_type");
		int count = versionCheckDao.getVersionCheckCount(osType);
		return String.valueOf(count);
	}
	
	private String date2Str(Timestamp timestamp) {

		java.util.Date d = timestamp;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(d);
	}
}
