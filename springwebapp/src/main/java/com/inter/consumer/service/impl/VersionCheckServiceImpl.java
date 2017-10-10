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
		String osType = param.get("osType");
		int currentVersionCodeParam = Integer.parseInt(param.get("currentVersionCode"));
		
		Map<String, Object> versionCheckInfo = versionCheckDao.getVersionCheckByOSType(osType);

		Map<String, Object> result = new HashMap<String, Object>();

		if (versionCheckInfo != null) {
			
			int currentVersionCodeDB = (Integer) versionCheckInfo.get("currentVersionCode");
			
			if (currentVersionCodeParam == currentVersionCodeDB) {
				versionCheckInfo.put("isForceUpdate", false);
			} else {
				int count = versionCheckDao.getVersionCheckCountByVersionCode(currentVersionCodeParam, currentVersionCodeDB);
				
				if (count > 0) {
					versionCheckInfo.put("isForceUpdate", true);
				} else {
					versionCheckInfo.put("isForceUpdate", false);
				}
			}
			
			result.putAll(versionCheckInfo);
			result.put("resultCode", 200);
		} else {
			result.put("resultCode", 500);
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
}
