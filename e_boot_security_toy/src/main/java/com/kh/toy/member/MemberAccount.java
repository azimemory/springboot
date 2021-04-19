package com.kh.toy.member;

import org.springframework.security.core.userdetails.User;

public class MemberAccount extends User{

	private static final long serialVersionUID = 1L;
	
	private Member member;

	public MemberAccount(Member member) {
		super(member.getUserId(), member.getPassword(), member.getAuthority());
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public String getEmail() {
		return member.getEmail();
	}

	public String getGrade() {
		return member.getGrade();
	}

	public String getTell() {
		return member.getTell();
	}
}
