package com.inter.consumer.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DetailInfoService {

	Map<String, Object> detailInfo(HttpServletRequest request);

}
