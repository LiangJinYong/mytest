package com.inter.consumer.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.VersionCheckDao;

@Repository
public class VersionCheckDaoImpl implements VersionCheckDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public int getVersionCheckCount(String osType) {
		int count = sqlSessionTemplate.selectOne("com.inter.consumer.getVersionCheckCount", osType);
		return count;
	}
	
}
