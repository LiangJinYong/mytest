package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.SignupSuperuserDao;

@Repository
public class SignupSuperuserDaoImpl implements SignupSuperuserDao {

	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	@Qualifier("orderSqlSession")
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void insertAppEnterprise(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertAppEnterprise", param);
	}

	public void insertAppEnterpriseUser(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertAppEnterpriseUser", param);
	}

}
