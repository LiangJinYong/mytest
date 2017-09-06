package com.inter.consumer.dao;

import java.util.Map;

public interface VersionCheckDao {

	Map<String, Object> getVersionCheckByOSType(String osType);

	int getVersionCheckCountByVersionCode(int currentVersionCodeParam, int currentVersionCodeDB);

}
