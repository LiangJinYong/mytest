package com.inter.consumer.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface VersionCheckService {

	Map<String, Object> versionCheck(HttpServletRequest request);

}