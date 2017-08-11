package com.inter.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.VersionCheckDao;
import com.inter.service.VersionCheckService;

@Service
public class VersionCheckServiceImpl implements VersionCheckService {

	@Autowired
	private VersionCheckDao versionCheckDao;
	
	public void setVersionCheckDao(VersionCheckDao versionCheckDao) {
		this.versionCheckDao = versionCheckDao;
	}

	public Map<String, Object> versionCheck(HttpServletRequest request) {
		try {
			Map<String, Object> query1Result = versionCheckDao.query1(request.getParameter("os_type"));
			int currentVersionCodeDb = (Integer) query1Result.get("current_version_code");
			int currentVersionCodeParam = Integer.parseInt(request.getParameter("current_version_code"));
			
			if (currentVersionCodeDb == currentVersionCodeParam) {
				query1Result.put("is_forced_update", false);
				query1Result.put("result_code", 200);
				return query1Result;
			} else {
				int count = versionCheckDao.query2(currentVersionCodeParam, currentVersionCodeDb);
				
				if (count > 0) {
					query1Result.put("is_forced_update", true);
					query1Result.put("result_code", 200);
					return query1Result;
				} else {
					query1Result.put("is_forced_update", false);
					query1Result.put("result_code", 200);
					return query1Result;
				}
			}
		} catch (EmptyResultDataAccessException e) {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result_code", 500);
			return result;
		}
	}
}
