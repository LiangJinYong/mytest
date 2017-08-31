package com.inter.consumer.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GetQuestionListService {

	Map<String, Object> getQuestionList(HttpServletRequest request);

}
