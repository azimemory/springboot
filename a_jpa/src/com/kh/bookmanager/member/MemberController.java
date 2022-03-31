package com.kh.bookmanager.member;

import java.time.LocalDateTime;
import java.util.List;

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	public Member searchById(String userId){
		return memberService.findMemberById(userId);
	}
 		
	public List<Member> searchAllMember(){
		return memberService.findAllMember();
	}
	
	public List<Member> searchByRegDate(String begin, String end){
		return memberService.findMemberByRegDate(LocalDateTime.parse(begin), LocalDateTime.parse(end));
	}
	
	public boolean join(Member member) {
		return memberService.persistMember(member);
	}

	public boolean modify(Member member) {
		return memberService.modifyMember(member);
	}
	
	public boolean delete(String userId){
		return memberService.deleteMember(userId);
	}
}
