package com.inter.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.dao.GetQuestionListDao;
import com.inter.service.GetQuestionListService;

@Service
public class GetQuestionListServiceImpl implements GetQuestionListService {

	@Autowired
	private GetQuestionListDao getQuestionListDao;

	public void setGetQuestionListDao(GetQuestionListDao getQuestionListDao) {
		this.getQuestionListDao = getQuestionListDao;
	}

	public Map<String, Object> getQuestionList(HttpServletRequest request) {

		String token = request.getHeader("token");

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
					dataMap.put("registration_time", date2Str((Timestamp) questionAnswer.get("registration_time")));
					dataMap.put("user_key", questionAnswer.get("user_key"));

					dataMap.put("answer", answerList);
					dataList.add(dataMap);
				}

				if (questionAnswer.get("answer_key") != null) {
					answerMap = new HashMap<String, Object>();
					answerMap.put("answer_key", questionAnswer.get("answer_key"));
					answerMap.put("content", questionAnswer.get("a_content"));
					answerMap.put("registration_time", date2Str((Timestamp)questionAnswer.get("a_registration_time")));

					answerList.add(answerMap);
				}

				lastQuestionKey = (Integer) questionAnswer.get("question_key");
			}

			if (dataList.size() > 0) {
				result.put("data", dataList);
			}

			result.put("result_code", 200);
		} else {
			result.put("result_code", 403);
		}

		return result;
	}

	private String date2Str(Timestamp timestamp) {

		java.util.Date d = timestamp;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(d);
	}

}
