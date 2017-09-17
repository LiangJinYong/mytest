package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.ConfirmMailCertificationCodeDao;

@Repository
public class ConfirmMailCertificationCodeDaoImpl implements ConfirmMailCertificationCodeDao {
	
	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public Map<String, String> queryCertificationCode(Map<String, String> param) {

		return sqlSessionTemplate.selectOne(NAMESPACE + "queryCertificationCode", param);
	}
}
