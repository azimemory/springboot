package com.kh.bookmanager.member.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.kh.bookmanager.member.model.dao.MemberDao;
import com.kh.bookmanager.member.model.vo.Member;

//Service
//웹어플리케이션의 비지니스 로직 작성
//사용자가 전송한 데이터를 Controller에게 전달 받고
//비지니스 로직을 위해 추가적으로 필요한 데이터를 Dao에게 요청해
//핵심로직인 비지니스로직을 작성한다.
//비지니스 로직을 Service가 담당하기 때문에 transaction관리도 Service가 담당.
public class MemberService {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("member");
	MemberDao memberDao = new MemberDao();
	
	public Member selectMemberById(String userId){	
		EntityManager em = emf.createEntityManager();
		Member member = memberDao.selectMemberById(em, userId);
		return member;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		EntityManager em = emf.createEntityManager();
		List<Member> memberList = memberDao.selectMemberByRegdate(em, begin, end);
		return memberList;
	}
 		
	public List<Member> selectMemberList(){
		EntityManager em = emf.createEntityManager();
		List<Member> memberList = memberDao.selectMemberList(em);
		return memberList;
	}
	
	public boolean insertMember(Member member){
		EntityManager em = emf.createEntityManager();
		//Transaction관리를 Service단에서 처리하기 위해 ection을 
		//Service의 메서드에서 생성
		boolean res = false;
		return res;
	}
	
	public boolean updateMember(Member member){
		EntityManager em = emf.createEntityManager();
		boolean res = false;
		return res;
	}
	
	public boolean deleteMember(String userId){
		EntityManager em = emf.createEntityManager();
		boolean res = false;
		return res;
	}
}
