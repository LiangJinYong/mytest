package com.inter.enterprise.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.inter.enterprise.dao.SendMailFmsDao;
import com.inter.enterprise.service.SendMailFmsService;
import com.inter.util.GetTimeUtil;
import com.inter.util.MakeCertificationCodeUtil;

@Service
public class SendMailFmsServiceImpl implements SendMailFmsService {

	@Autowired
	private SendMailFmsDao sendMailFmsDao;

	@Autowired
	private MailSender mailSender;

	public void setSendMailFmsDao(SendMailFmsDao sendMailFmsDao) {
		this.sendMailFmsDao = sendMailFmsDao;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String sendMailFms(String mailJson) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		Gson gson = new Gson();
		mailJson = "{\"mail\":\"liangjy881@naver.com\",\"type\":\"approve\",\"message\":{\"biz_name\":\"杭州联数信息科技有限公司\",\"corp_name\":\"缪志荣\",\"corp_phone_num\":\"13810180818\",\"corp_mail\":\"2320606974@qq.com\",\"corp_addr\":\"杭州市萧山区北干街道萧山商会大厦A座16楼\",\"corp_homepage\":\"www.lianshukj.com\",\"join_dt\":\"2017-09-10\",\"approve_dt\":\"2017-09-25\",\"approve_id\":\"test / Rui\"}}";
		Map<String, Object> mailObj = gson.fromJson(mailJson, Map.class);
		String type = (String) mailObj.get("type");
		String mail = (String) mailObj.get("mail");
		Map<String, String> message = (Map<String, String>) mailObj.get("message");

		String certificationCode = MakeCertificationCodeUtil.makeCertificationCode();
		String time = GetTimeUtil.getTime();

		Map<String, String> param = new HashMap<String, String>();
		param.put("mail", mail);
		param.put("certification_code", certificationCode);
		param.put("time", time);

		String subject = "";
		StringBuilder body = new StringBuilder();

		if ("join".equals(type)) {
			subject = "防伪溯源企业注册申请";
			body.append("企业申请注册会员已完成.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("法人姓名 : ").append(message.get("corp_name")).append("\n")//
					.append("法人联系方式 : ").append(message.get("corp_phone_num")).append("\n")//
					.append("公司 E-mail : ").append(message.get("corp_mail")).append("\n")//
					.append("公司地址 : ").append(message.get("corp_addr")).append("\n")//
					.append("公司网址 : ").append(message.get("corp_homepage")).append("\n")//
					.append("注册日期 : ").append(message.get("join_dt"));
		} else if ("approve".equals(type)) {
			subject = "防伪溯源企业注册审批";
			body.append("企业申请注册会员已完成.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("法人姓名 : ").append(message.get("corp_name")).append("\n")//
					.append("法人联系方式 : ").append(message.get("corp_phone_num")).append("\n")//
					.append("公司 E-mail : ").append(message.get("corp_mail")).append("\n")//
					.append("公司地址 : ").append(message.get("corp_addr")).append("\n")//
					.append("公司网址 : ").append(message.get("corp_homepage")).append("\n")//
					.append("注册日期 : ").append(message.get("join_dt")).append("\n")//
					.append("审批日期 : ").append(message.get("approve_dt")).append("\n")//
					.append("审批人员ID : ").append(message.get("approve_id")).append("\n")//
					.append("验证码 : ").append(certificationCode);
		} else if ("insert".equals(type)) {
			subject = "企业成员注册完成";
			body.append("企业成员Id生成.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("法人姓名 : ").append(message.get("corp_name")).append("\n")//
					.append("法人联系方式 : ").append(message.get("corp_phone_num")).append("\n")//
					.append("公司 E-mail : ").append(message.get("corp_mail")).append("\n")//
					.append("企业成员ID 生成日期 : ").append(message.get("make_id_dt")).append("\n")//
					.append("企业成员ID : ").append(message.get("user_id")).append("\n")//
					.append("企业成员密码 : ").append(message.get("user_pwd")).append("\n")//
					.append("企业成员姓名 : ").append(message.get("user_name")).append("\n")//
					.append("企业成员邮箱 : ").append(message.get("user_email")).append("\n")//
					.append("验证码 : ").append(certificationCode);
		} else if ("request".equals(type)) {
			subject = "标签申请详细内容";
			body.append("成标签申请已完成.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("商品名称 : ").append(message.get("product_name")).append("\n")//
					.append("商品代码 : ").append(message.get("product_no")).append("\n")//
					.append("标签数量 : ").append(message.get("order_cnt")).append("\n")//
					.append("申请人姓名 : ").append(message.get("requestor_name")).append("\n")//
					.append("申请日期 : ").append(message.get("order_dt")).append("\n")//
					.append("审批人员ID : ").append(message.get("approve_id")).append("\n")//
					.append("审批日期 : ").append(message.get("approve_dt"));
		} else if ("reject".equals(type)) {
			subject = "驳回标签申请";
			body.append("标签申请被驳回.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("商品名称 : ").append(message.get("product_name")).append("\n")//
					.append("商品代码 : ").append(message.get("product_no")).append("\n")//
					.append("标签数量 : ").append(message.get("order_cnt")).append("\n")//
					.append("申请人姓名 : ").append(message.get("requestor_name")).append("\n")//
					.append("申请日期 : ").append(message.get("order_dt")).append("\n")//
					.append("驳回人员ID : ").append(message.get("reject_id")).append("\n")//
					.append("驳回日期 : ").append(message.get("reject_dt")).append("\n")//
					.append("驳回原因 : ").append(message.get("reject_comment"));
		} else if ("handle".equals(type)) {
			subject = "标签处理结果";
			body.append("申请的标签已处理完成.\n\n")//
					.append("企业名称 : ").append(message.get("biz_name")).append("\n")//
					.append("商品名称 : ").append(message.get("product_name")).append("\n")//
					.append("商品代码 : ").append(message.get("product_no")).append("\n")//
					.append("标签数量 : ").append(message.get("order_cnt")).append("\n")//
					.append("申请人姓名 : ").append(message.get("requestor_name")).append("\n")//
					.append("申请日期 : ").append(message.get("order_dt")).append("\n")//
					.append("完成日期 : ").append(message.get("return_dt"));
		}

		try {
			sendMailFmsDao.insertMailCertificationCode(param);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom("lianshu@lianshukj.com");
			simpleMailMessage.setTo(mail);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body.toString());
			mailSender.send(simpleMailMessage);

			result.put("result_code", 200);
		} catch (Exception e) {
			result.put("result_code", 500);
			e.printStackTrace();
		}
		return gson.toJson(result);
	}

}
