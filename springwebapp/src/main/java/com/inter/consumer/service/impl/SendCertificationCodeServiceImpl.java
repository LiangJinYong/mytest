package com.inter.consumer.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.SendCertificationCodeDao;
import com.inter.consumer.service.SendCertificationCodeService;
import com.inter.util.HttpUtil;

import net.sf.json.JSONObject;

@Service
public class SendCertificationCodeServiceImpl implements SendCertificationCodeService {

	@Autowired
	private SendCertificationCodeDao sendCertificationCodeDao;

	public void setSendCertificationCodeDao(SendCertificationCodeDao sendCertificationCodeDao) {
		this.sendCertificationCodeDao = sendCertificationCodeDao;
	}

	public Map<String, Object> sendCertificationCode(HttpServletRequest request) {
		String mobilePhoneNumber = request.getParameter("mobile_phone_number");

		String certificationCode = makeCertificationCode();

		String time = getTime();

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			sendCertificationCodeDao.insertCertificationCode(mobilePhoneNumber, certificationCode, time);
			
			String appKey = "38eac23f5c3697c1";
			String content = "验证码 : " + certificationCode + "【联数科技】";
			String URL = "http://api.jisuapi.com/sms/send";

			Map<String, String> datas = new HashMap<String, String>();
			datas.put("appkey", appKey);
			datas.put("mobile", mobilePhoneNumber);
			datas.put("content", content);

			int status = Integer.parseInt(sendPost(URL, datas));

			if (status == 0) {
				result.put("result_code", 200);
			} else {
				result.put("result_code", 503);
			}
		} catch (Exception e) {
			result.put("result_code", 500);
		}
		return result;
	}

	private static String sendPost(String URL, Map<String, String> datas) {
		String result = null;
		try {
			result = HttpUtil.sendPost(URL, datas, "utf-8");

			JSONObject json = JSONObject.fromObject(result);
			if (json.getInt("status") != 0) {
				System.out.println(json.getString("msg"));
			} else {
				JSONObject resultarr = json.optJSONObject("result");
				String count = resultarr.getString("count");
				String accountid = resultarr.getString("accountid");
				System.out.println(count + " " + accountid);
			}

			return json.getString("status");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	private String makeCertificationCode() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int randomInt = random.nextInt(10);
			sb.append(randomInt);
		}

		return sb.toString();
	}

	private String getTime() {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date();

		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return df.format(curDate);
	}
}
