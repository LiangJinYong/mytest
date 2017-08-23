package com.inter.dao;

public interface SendCertificationCodeDao {

	void insertCertificationCode(String mobilePhoneNumber, String certificationCode, String time);

}
