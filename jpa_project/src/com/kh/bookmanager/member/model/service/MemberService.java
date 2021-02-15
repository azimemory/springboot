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
		Member member = memberDao.selectMemberById( userId);
		return member;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		List<Member> memberList = memberDao.selectMemberByRegdate( begin, end);
		return memberList;
	}
 		
	public ArrayList<Member> selectMemberList(){
		ArrayList<Member> memberList = memberDao.selectMemberList();
		return memberList;
	}
	
	public int insertMember(Member member){
		//Transaction관리를 Service단에서 처리하기 위해 ection을 
		//Service의 메서드에서 생성
		int res = 0;
		return res;
	}
	
	public int updateMember(Member member){
		int res = 0;
		return res;
	}
	
	public int deleteMember(String userId){
		int res = 0;
		return res;
	}
}
