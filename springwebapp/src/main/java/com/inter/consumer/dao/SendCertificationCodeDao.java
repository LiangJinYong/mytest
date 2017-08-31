package com.inter.consumer.dao;

public interface SendCertificationCodeDao {

	void insertCertificationCode(String mobilePhoneNumber, String certificationCode, String time);

}
