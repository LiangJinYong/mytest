package com.inter.consumer.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.consumer.dao.SendCertificationCodeDao;
import com.inter.consumer.service.SendCertificationCodeService;
import com.inter.util.GetTimeUtil;
import com.inter.util.HttpUtil;
import com.inter.util.MakeCertificationCodeUtil;

import net.sf.json.JSONObject;

@Service
public class SendCertificationCodeServiceImpl implements SendCertificationCodeService {

	@Autowired
	private SendCertificationCodeDao sendCertificationCodeDao;

	public void setSendCertificationCodeDao(SendCertificationCodeDao sendCertificationCodeDao) {
		this.sendCertificationCodeDao = sendCertificationCodeDao;
	}

	public String sendCertificationCode(Map<String, String> param) {
		String mobilePhoneNumber = param.get("mobile_phone_number");

		String certificationCode = MakeCertificationCodeUtil.makeCertificationCode();

		String time = GetTimeUtil.getTime();

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			param.put("certificationCode", certificationCode);
			param.put("time", time);

			sendCertificationCodeDao.insertCertificationCode(param);

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

		Gson gson = new Gson();

		return gson.toJson(result);
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
		return "-1";

	}

}
