package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FailLogService {

	Map<String, Object> failLog(HttpServletRequest request);

}
