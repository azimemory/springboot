package com.kh.toy.common.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.kh.toy.common.code.Config;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;

@Component
public class EmailSender {

	JavaMailSender mailSender;
	
	public EmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void send(String to, String subject, String htmlTxt) {
		
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			msg.setFrom(Config.COMPANY_EMAIL.DESC);
			msg.setRecipients(Message.RecipientType.TO, to);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(htmlTxt,"UTF-8","html");
			mailSender.send(msg);
			
		} catch (MessagingException e) {
			throw new HandlableException(ErrorCode.MAIL_SEND_FAIL_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
