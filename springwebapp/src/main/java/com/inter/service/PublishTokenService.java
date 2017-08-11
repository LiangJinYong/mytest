package com.inter.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PublishTokenService {

	Map<String, Object> publishToken(HttpServletRequest request);

}
