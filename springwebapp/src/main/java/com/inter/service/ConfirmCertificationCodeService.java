package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ConfirmCertificationCodeService {

	Map<String, Object> confirmCertificationCode(HttpServletRequest request);

}
