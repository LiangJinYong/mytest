package com.inter.consumer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.GetQuestionListDao;
import com.inter.consumer.service.GetQuestionListService;

@Service
public class GetQuestionListServiceImpl implements GetQuestionListService {

	@Autowired
	private GetQuestionListDao getQuestionListDao;

	public void setGetQuestionListDao(GetQuestionListDao getQuestionListDao) {
		this.getQuestionListDao = getQuestionListDao;
	}

	public String getQuestionList(Map<String, String> param) {
		String token = param.get("token");

		int appUserCount = getQuestionListDao.queryAppUserCount(token);

		Map<String, Object> result = new HashMap<String, Object>();

		if (appUserCount > 0) {

			List<Map<String, Object>> questionAnswerList = getQuestionListDao.queryQuestionAnswerList(token);

			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

			int lastQuestionKey = 0;

			Map<String, Object> dataMap = new HashMap<String, Object>();

			List<Map<String, Object>> answerList = new ArrayList<Map<String, Object>>();

			Map<String, Object> answerMap = new HashMap<String, Object>();

			for (Map<String, Object> questionAnswer : questionAnswerList) {

				if (lastQuestionKey != (Integer) questionAnswer.get("question_key") || lastQuestionKey == 0) {
					dataMap = new HashMap<String, Object>();
					answerList = new ArrayList<Map<String, Object>>();

					dataMap.put("question_key", questionAnswer.get("question_key"));
					dataMap.put("content", questionAnswer.get("content"));
					dataMap.put("registration_time", questionAnswer.get("registration_time"));
					dataMap.put("user_key", questionAnswer.get("user_key"));

					dataMap.put("answer", answerList);
					dataList.add(dataMap);
				}

				if (questionAnswer.get("answer_key") != null) {
					answerMap = new HashMap<String, Object>();
					answerMap.put("answer_key", questionAnswer.get("answer_key"));
					answerMap.put("content", questionAnswer.get("a_content"));
					answerMap.put("registration_time", questionAnswer.get("a_registration_time"));

					answerList.add(answerMap);
				}

				lastQuestionKey = (Integer) questionAnswer.get("question_key");
			}

			result.put("data", dataList);
			result.put("result_code", 200);
		} else {
			result.put("result_code", 403);
		}

		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
