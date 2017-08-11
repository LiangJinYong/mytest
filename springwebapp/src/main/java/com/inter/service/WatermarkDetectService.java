package com.inter.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WatermarkDetectService {

	void watermarkDetect(HttpServletRequest request, HttpServletResponse response);

}
