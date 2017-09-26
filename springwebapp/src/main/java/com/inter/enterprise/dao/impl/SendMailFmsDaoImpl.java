package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.SendMailFmsDao;

@Repository
public class SendMailFmsDaoImpl implements SendMailFmsDao {

	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void insertMailCertificationCode(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertMailCertificationCode", param);
	}

}
