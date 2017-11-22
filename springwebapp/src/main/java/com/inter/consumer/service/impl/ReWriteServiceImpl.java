package com.inter.consumer.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.ReWriteDao;
import com.inter.consumer.service.ReWriteService;
import com.inter.util.AesModule;

@Service
public class ReWriteServiceImpl implements ReWriteService {

	@Autowired
	private ReWriteDao reWriteDao;

	public void setReWriteDao(ReWriteDao reWriteDao) {
		this.reWriteDao = reWriteDao;
	}

	public String reWrite(Map<String, String> param) {
		String watermarkKey = param.get("watermarkKey");
		watermarkKey = watermarkKey.replace(" ", "+");
		String sequence = AesModule.aesDecrypt(watermarkKey);
		
		long seq = Long.parseLong(sequence);
		String binary = Long.toBinaryString(seq);
		while (binary.length() < 48) {
		    binary = "0" + binary;
		}

		Integer orderNumber = reWriteDao.getOrderNumberBySequence(binary);

		String result = "";
		if (orderNumber != null) {
			String homepageAddr = reWriteDao.queryHomepageAddr(orderNumber);

			if (homepageAddr != null && !homepageAddr.equals("")) {
				result = "<meta http-equiv='refresh' content='0; url=" + homepageAddr + "'>";
			}
		}

		return result;
	}

}
