package com.kh.toy.member;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kh.toy.entity.Member;

public class MemberAccount extends User{
	
	private static final long serialVersionUID = -7817712196075581742L;
	private Member member;
	
	public MemberAccount(Member member) {
		super(member.getUserId(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getGrade())));
		this.member =  member;
	}
	
	public Member getMember() {
		return member;
	}
	
	public String getPassword() {
		return member.getPassword();
	}
	
	public String getEmail() {
		return member.getEmail();
	}

	public String getTell() {
		return member.getTell();
	}
	
	public String getGrade() {
		return member.getGrade();
	}
	
	
	
	
	
	
	
}
