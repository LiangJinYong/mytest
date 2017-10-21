package com.inter.consumer.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.VersionCheckDao;

@Repository
public class VersionCheckDaoImpl implements VersionCheckDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Map<String, Object> getVersionCheckByOSType(String osType) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "getVersionCheckByOSType", osType);
	}

	public int getVersionCheckCountByVersionCode(int currentVersionCodeParam, int currentVersionCodeDB) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("currentVersionCodeParam", currentVersionCodeParam);
		paramMap.put("currentVersionCodeDB", currentVersionCodeDB);
		return sqlSessionTemplate.selectOne(NAMESPACE + "getVersionCheckCountByVersionCode", paramMap);
	}

}
