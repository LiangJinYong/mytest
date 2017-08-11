package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Step2QrService {

	Map<String, Object> step2Qr(HttpServletRequest request, HttpServletResponse response);

}
