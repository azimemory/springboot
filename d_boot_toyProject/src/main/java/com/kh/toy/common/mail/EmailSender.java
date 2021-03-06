package com.kh.toy.common.mail;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.kh.toy.common.code.Code;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;

@Component
@EnableAsync  //해당 클래스에서 Async 어노테이션을 사용할 것을 등록
public class EmailSender {
	
	@Autowired 
	JavaMailSender mailSender;
	
	//해당 메서드를 새로운 thread를 만들어 비동기로 실행. public 메서드만 가능하다. 
	//반환형이 있다면 Future타입의 객체를 반환시켜 반환값이 있는 비동기 메서드로도 만들 수 있다.
	@Async
	public void sendEmail(String to, String subject, String htmlText) {
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			msg.setFrom(new InternetAddress(Code.EMAIL.desc));
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(getMultipart(htmlText)); //message의 바디에 추가
	        mailSender.send(msg);
		} catch(Exception e) {
			throw new ToAlertException(ErrorCode.MAIL_SENDING_ERROR,e);
		}
	}
	
	private Multipart getMultipart(String htmlText) throws MessagingException{
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlText, "text/html; charset=UTF-8");
        mp.addBodyPart(htmlPart);
        return mp;
	}
}
