package com.inter.consumer.dao;

import java.util.Map;

public interface RegistrationQuestionDao {

	Integer queryAppUserNoByToken(String token);

	void insertAppQuestion(Map<String, String> param);

}
