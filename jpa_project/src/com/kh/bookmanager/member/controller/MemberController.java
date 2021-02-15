package com.kh.bookmanager.member.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.kh.bookmanager.member.model.service.MemberService;
import com.kh.bookmanager.member.model.vo.Member;

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	public Member searchById(String userId){
		return memberService.selectMemberById(userId);
	}
 		
	public ArrayList<Member> searchAllMember(){
		return memberService.selectMemberList();
	}
	
	public int join(Member member) {
		return memberService.insertMember(member);
	}

	public int modify(Member member) {
		return memberService.updateMember(member);
	}
	
	public int delete(String userId){
		return memberService.deleteMember(userId);
	}
	
	public List<Member> searchByRegDate(String begin, String end){
		Date beginDate = Date.valueOf(begin);
		Date endDate = Date.valueOf(end);
		return memberService.selectMemberByRegdate(beginDate, endDate);
	}
}
