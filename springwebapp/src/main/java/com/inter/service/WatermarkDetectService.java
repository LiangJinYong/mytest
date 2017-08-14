package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WatermarkDetectService {

	Map<String, Object> watermarkDetect(HttpServletRequest request);

}
