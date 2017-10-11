package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.SendMailCertificationCodeDao;
import com.inter.enterprise.service.SendMailCertificationCodeService;
import com.inter.util.GetTimeUtil;
import com.inter.util.MakeCertificationCodeUtil;

@Service
public class SendMailCertificationCodeServiceImpl implements SendMailCertificationCodeService {

	@Autowired
	private SendMailCertificationCodeDao sendMailCertificationCodeDao;

	@Autowired
	private MailSender mailSender;

	public void setSendMailCertificationCodeDao(SendMailCertificationCodeDao sendMailCertificationCodeDao) {
		this.sendMailCertificationCodeDao = sendMailCertificationCodeDao;
	}

	public String sendMailCertificationCode(Map<String, String> param) {
		Map<String, Object> result = new HashMap<String, Object>();

		int count = sendMailCertificationCodeDao.queryAppEnterpriseCount(param);

		if (count == 0) {
			String certificationCode = MakeCertificationCodeUtil.makeCertificationCode();
			String time = GetTimeUtil.getTime();
			param.put("certification_code", certificationCode);
			param.put("time", time);

			try {
				sendMailCertificationCodeDao.insertMailCertificationCode(param);

				SimpleMailMessage message = new SimpleMailMessage();

				String mail = param.get("mail");

				message.setFrom("lianshu@lianshukj.com");
				message.setTo(mail);
				message.setSubject("【杭州联数信息科技】发送验证码");
				message.setText("您的验证码是 :" + certificationCode);
				mailSender.send(message);

				result.put("resultCode", 200);
			} catch (Exception e) {
				result.put("resultCode", 500);
				e.printStackTrace();
			}
		} else {
			result.put("resultCode", 409);
		}

		Gson gson = new Gson();
		return gson.toJson(result);
	}

}
