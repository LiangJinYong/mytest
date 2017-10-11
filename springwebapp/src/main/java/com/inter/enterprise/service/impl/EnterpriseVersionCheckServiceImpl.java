package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.EnterpriseVersionCheckDao;
import com.inter.enterprise.service.EnterpriseVersionCheckService;

@Service
public class EnterpriseVersionCheckServiceImpl implements EnterpriseVersionCheckService {

	@Autowired
	private EnterpriseVersionCheckDao enterpriseVersionCheckDao;
	
	public void setEnterpriseVersionCheckDao(EnterpriseVersionCheckDao enterpriseVersionCheckDao) {
		this.enterpriseVersionCheckDao = enterpriseVersionCheckDao;
	}

	public String versionCheck(Map<String, String> param) {
		
		String osType = param.get("osType");
		int currentVersionCodeParam = Integer.parseInt(param.get("currentVersionCode"));
		
		Map<String, Object> versionCheckInfo = enterpriseVersionCheckDao.getVersionCheckByOSType(osType);

		Map<String, Object> result = new HashMap<String, Object>();

		if (versionCheckInfo != null) {
			
			int currentVersionCodeDB = (Integer) versionCheckInfo.get("currentVersionCode");
			
			if (currentVersionCodeParam == currentVersionCodeDB) {
				versionCheckInfo.put("isForceUpdate", false);
			} else {
				int count = enterpriseVersionCheckDao.getVersionCheckCountByVersionCode(currentVersionCodeParam, currentVersionCodeDB);
				
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
