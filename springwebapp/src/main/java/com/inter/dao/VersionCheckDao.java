package com.inter.dao;

import java.util.Map;

public interface VersionCheckDao {

	Map<String, Object> query1(String osType);

	int query2(int currentVersionCodeParam, int currentVersionCodeDb);
}
