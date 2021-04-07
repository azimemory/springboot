package com.kh.bookmanager.member;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	public Member searchById(String userId){
		return memberService.findMemberById(userId);
	}
 		
	public List<Member> searchAllMember(){
		return memberService.findMemberAll();
	}
	
	public List<Member> searchByRegDate(String begin, String end){
		
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(Date.valueOf(begin));
		
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(Date.valueOf(end));
		
		return memberService.findMemberByRegDate(beginDate, endDate);
	}
	
	public boolean join(Member member) {
		return memberService.persistMember(member);
	}

	public boolean modify(Member member) {
		return memberService.modifyPassword(member);
	}
	
	public boolean delete(String userId){
		return memberService.deleteMember(userId);
	}
}
