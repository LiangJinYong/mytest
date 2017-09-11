package com.inter.consumer.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.consumer.dao.ReWriteDao;

@Repository
public class ReWriteDaoImpl implements ReWriteDao {

	private static final String NAMESPACE = "com.inter.consumer.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<String> queryHomepageAddr(String watermarkKey) {
		
		return sqlSessionTemplate.selectList(NAMESPACE + "queryHomepageAddr", watermarkKey);
	}

}
