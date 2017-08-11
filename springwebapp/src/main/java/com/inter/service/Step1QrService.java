package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Step1QrService {

	Map<String, Object> step1Qr(HttpServletRequest request, HttpServletResponse response);

}
