package com.app.consumer.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.app.consumer.dao.VersionCheckDao;

@Repository
public class VersionCheckDaoImpl implements VersionCheckDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	ApplicationContext ac;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List findAll() {
		return sqlSessionTemplate.selectList("com.abc.findAll");
	}

}
