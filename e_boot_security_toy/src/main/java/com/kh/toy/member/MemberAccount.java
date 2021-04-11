package com.kh.toy.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberAccount extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Member member;
	private static List<SimpleGrantedAuthority> AUTH;
	
	static{
		AUTH =  new ArrayList<SimpleGrantedAuthority>();
		AUTH.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	public MemberAccount(Member member) {
		super(member.getUserId(), member.getPassword(), AUTH);
		this.member = member;
	}
}
