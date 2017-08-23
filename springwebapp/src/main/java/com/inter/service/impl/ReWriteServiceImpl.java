package com.inter.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.dao.ReWriteDao;
import com.inter.service.ReWriteService;

@Service
public class ReWriteServiceImpl implements ReWriteService {

	@Autowired
	private ReWriteDao reWriteDao;

	public void setReWriteDao(ReWriteDao reWriteDao) {
		this.reWriteDao = reWriteDao;
	}

	public String reWrite(HttpServletRequest request) {
		String watermarkKey = request.getParameter("watermark_key");
		
		List<String> homepageAddr = reWriteDao.queryHomepageAddr(watermarkKey);
		
		String result = "";
		
		if (homepageAddr.size() > 0) {
			result = "<meta http-equiv='refresh' content='0; url=" + homepageAddr.get(0) + "'>";
		}
		
		return result;
	}
	
	
}
