package com.inter.consumer.dao;

import java.util.Map;

public interface VersionCheckDao {

	Map<String, Object> queryAppVersionByOsType(String osType);

	int queryAppVersionByCode(int currentVersionCode, int currentVersionCodeDb);
}
