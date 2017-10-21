package com.inter.consumer.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ConfirmCertificationCodeDao;

@Repository
public class ConfirmCertificationCodeDaoImpl implements ConfirmCertificationCodeDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public String queryCertificationCode(String mobilePhoneNumber) {
		
		return sqlSessionTemplate.selectOne(NAMESPACE + "queryCertificationCode", mobilePhoneNumber);
	}
	
}
