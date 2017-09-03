package com.inter.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.enterprise.dao.EnterpriseVersionCheckDao;
import com.inter.enterprise.service.EnterpriseVersionCheckService;

@Service
public class EnterpriseVersionCheckServiceImpl implements EnterpriseVersionCheckService {

	@Autowired
	private EnterpriseVersionCheckDao enterpriseVersionCheckDao;
	
	public void setEnterpriseVersionCheckDao(EnterpriseVersionCheckDao enterpriseVersionCheckDao) {
		this.enterpriseVersionCheckDao = enterpriseVersionCheckDao;
	}


}
