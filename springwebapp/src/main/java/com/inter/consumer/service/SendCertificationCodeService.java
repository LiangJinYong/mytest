package com.inter.consumer.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SendCertificationCodeService {

	Map<String, Object> sendCertificationCode(HttpServletRequest request);
}
