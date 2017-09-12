package com.inter.enterprise.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.inter.enterprise.dao.SendMailCertificationCodeService;

@Service
public class SendMailCertificationCodeServiceImpl implements SendMailCertificationCodeService {

	@Autowired
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String sendMailCertificationCode(Map<String, String> param) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("lianshu@lianshukj.com");
		message.setTo("liangjy881@naver.com");
		message.setSubject("[mail test]");
		message.setText("너를 당황 시켜려는 못된 맘이 있었거나\n 이결혼 무효야 괜히 훼방 한번 놓으려고 간거는 아니니까");
		mailSender.send(message);
		
		return null;
	}

}
