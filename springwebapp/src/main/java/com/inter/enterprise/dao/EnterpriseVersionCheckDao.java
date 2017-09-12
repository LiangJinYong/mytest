package com.inter.enterprise.dao;

import java.util.Map;

public interface EnterpriseVersionCheckDao {

	Map<String, Object> getVersionCheckByOSType(String osType);

	int getVersionCheckCountByVersionCode(int currentVersionCodeParam, int currentVersionCodeDB);


}
