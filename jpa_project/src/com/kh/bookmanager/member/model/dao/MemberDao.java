package com.kh.bookmanager.member.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.kh.bookmanager.member.model.vo.Member;

//DAO : DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 가능하다면 하나의 메서드에 하나의 쿼리만 처리하도록 작성
public class MemberDao {

	public MemberDao() {}
	
	public Member selectMemberById(EntityManager em, String userId){
		return null;
	}
 		
	public List<Member> selectMemberList(EntityManager em){		
		return null;
	}
	
	public boolean insertMember(EntityManager em, Member member){
		return false;
	}
	
	public boolean updateMember(EntityManager em, Member member){
		return false;
	}
	
	public boolean deleteMember(EntityManager em, String userId){
		return false;
	}
	
	public List<Member> selectMemberByRegdate(EntityManager em, Date begin, Date end){
		List<Member> memberList = new ArrayList<>();
		
		return memberList;
	}
}
