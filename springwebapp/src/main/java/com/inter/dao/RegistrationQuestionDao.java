package com.inter.dao;

public interface RegistrationQuestionDao {

	int queryAppUserCount(String token);

	int queryUserKey(String token);

	void insertAppQuestion(String content, String time, int userKey);

}
