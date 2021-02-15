package com.kh.bookmanager.member.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.kh.bookmanager.member.model.vo.Member;

//DAO : DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 가능하다면 하나의 메서드에 하나의 쿼리만 처리하도록 작성
public class MemberDao {

	public MemberDao() {}
	
	public Member selectMemberById( String userId){
		Member member = null;		
		return member;
	}
 		
	public ArrayList<Member> selectMemberList(){		
		ArrayList<Member> memberList = new ArrayList<Member>();		
		return memberList;
	}
	
	public int insertMember( Member member){
		int res = 0;
		return res;
	}
	
	public int updateMember( Member member){
		int res = 0;		
		return res;
	}
	
	public int deleteMember( String userId){
		int res = 0;		
		return res;
	}
	
	public List<Member> selectMemberByRegdate( Date begin, Date end){
		List<Member> memberList = new ArrayList<>();
		
		return memberList;
	}
}
