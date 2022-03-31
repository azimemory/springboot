package com.kh.spring.member.validator;

import com.kh.spring.entity.Member;

import lombok.Data;

@Data
public class JoinForm {
	
	private String userId;
	private String password;
	private String tell;
	private String email;
	
	public Member convertToMember() {
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setEmail(email);
		member.setTell(tell);
		return member;
	}
	
	
	
}
