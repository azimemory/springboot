package com.kh.bookmanager.member;

import java.util.List;

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	public Member searchById(String userId){
		return null;
	}
 		
	public List<Member> searchAllMember(){
		return null;
	}
	
	public boolean join(Member member) {
		return false;
	}

	public boolean modify(Member member) {
		return false;
	}
	
	public boolean delete(String userId){
		return false;
	}
	
	public List<Member> searchByRegDate(String begin, String end){
		return null;
	}
}
