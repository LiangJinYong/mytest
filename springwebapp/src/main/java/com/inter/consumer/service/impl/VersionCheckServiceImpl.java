package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.VersionCheckDao;
import com.inter.consumer.service.VersionCheckService;

@Service
public class VersionCheckServiceImpl implements VersionCheckService {

	@Autowired
	private VersionCheckDao versionCheckDao;
	
	public void setVersionCheckDao(VersionCheckDao versionCheckDao) {
		this.versionCheckDao = versionCheckDao;
	}

	public String versionCheck(Map<String, String> param) {
		String osType = param.get("os_type");
		int currentVersionCodeParam = Integer.parseInt(param.get("current_version_code"));
		
		Map<String, Object> versionCheckInfo = versionCheckDao.getVersionCheckByOSType(osType);

		Map<String, Object> result = new HashMap<String, Object>();

		if (versionCheckInfo != null) {
			
			int currentVersionCodeDB = (Integer) versionCheckInfo.get("current_version_code");
			
			if (currentVersionCodeParam == currentVersionCodeDB) {
				versionCheckInfo.put("is_forced_update", false);
			} else {
				int count = versionCheckDao.getVersionCheckCountByVersionCode(currentVersionCodeParam, currentVersionCodeDB);
				
				if (count > 0) {
					versionCheckInfo.put("is_forced_update", true);
				} else {
					versionCheckInfo.put("is_forced_update", false);
				}
			}
			
			result.putAll(versionCheckInfo);
			result.put("result_code", 200);
			System.out.println(result);
		} else {
			result.put("result_code", 500);
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
}
