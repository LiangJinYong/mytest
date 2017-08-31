package com.inter.consumer.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public Map<String, Object> versionCheck(HttpServletRequest request) {
		
		String osType = request.getParameter("os_type");
		int currentVersionCode = Integer.parseInt(request.getParameter("current_version_code"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			result = versionCheckDao.queryAppVersionByOsType(osType);
			
			result.put("UPDATE_DTTM", date2Str((Timestamp)result.get("UPDATE_DTTM")));
			
			int currentVersionCodeDb = (Integer) result.get("current_version_code");
			if (currentVersionCode == currentVersionCodeDb) {
				result.put("is_forced_update", false);
				result.put("result_code", 200);
				
			} else {
				int count = versionCheckDao.queryAppVersionByCode(currentVersionCode, currentVersionCodeDb);
				
				if (count > 0) {
					result.put("is_forced_update", true);
					result.put("result_code", 200);
				} else {
					result.put("is_forced_update", false);
					result.put("result_code", 200);
				}
			}
		} catch (EmptyResultDataAccessException e) {
			result.put("result_code", 500);
		}
		return result;
	}
	
	private String date2Str(Timestamp timestamp) {

		java.util.Date d = timestamp;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(d);
	}
}
