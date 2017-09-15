package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inter.enterprise.dao.RequestStickerDao;

@Repository
public class RequestStickerDaoImpl implements RequestStickerDao {
	
	private static final String NAMESPACE = "com.inter.enterprise.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void insertRequestSticker(Map<String, String> param) {
		sqlSessionTemplate.insert(NAMESPACE + "insertRequestSticker", param);
	}
}
