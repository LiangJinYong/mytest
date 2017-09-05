package com.inter.consumer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.DetailInfoDao;
import com.inter.consumer.service.DetailInfoService;

@Service
public class DetailInfoServiceImpl implements DetailInfoService {

	@Autowired
	private DetailInfoDao detailInfoDao;

	public void setDetailInfoDao(DetailInfoDao detailInfoDao) {
		this.detailInfoDao = detailInfoDao;
	}

	public String detailInfo(Map<String, String> paramMap) {
		String productKey = paramMap.get("PRODUCT_KEY");
		
		String result = detailInfoDao.getDetailInfo(productKey);
		return result;
	}

}
