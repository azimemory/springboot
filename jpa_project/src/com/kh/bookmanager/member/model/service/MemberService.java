package com.kh.bookmanager.member.model.service;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.kh.bookmanager.member.model.dao.MemberDao;
import com.kh.bookmanager.member.model.vo.Member;

public class MemberService {
	
	//EntityManagerFactory는 threadSafe하기 때문에 전역에 선언
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	MemberDao memberDao = new MemberDao();
	
	public Member selectMemberById(String userId){
		//EntityManager는 threadSafe하지 않기 때문에 지역에 선언해준다.
		EntityManager em = emf.createEntityManager();
		Member member = null;
		
		try {
			member = memberDao.selectMemberById(em, userId);
		} finally {
			em.close();
		}
		return member;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		List<Member> memberList = null;
		EntityManager em = emf.createEntityManager();
		
		try {
			memberList = memberDao.selectMemberByRegdate(em, begin, end);
		} finally {
			em.close();
		}
		
		return memberList;
	}
 		
	public List<Member> selectMemberList(){
		List<Member> memberList = null;
		EntityManager em = emf.createEntityManager(); // 1. 영속성컨텍스트 생성
		EntityTransaction tx = em.getTransaction(); 
		try {
			memberList = memberDao.selectMemberList(em); //3. 회원정보 조회
		} finally {
			em.close(); //4. 영속성컨텍스트 종료
		}
		
		return memberList;
	}
	
	public boolean insertMember(Member member){
		boolean res = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			memberDao.insertMember(em, member);
			tx.commit();
			res = true;
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		return res;
	}
		
	public boolean updateMember(Member member){
		boolean res = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			memberDao.updateMember(em, member);
			tx.commit();
			res = true;
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		
		return res;
	}
	
	public boolean deleteMember(String userId){
		boolean res = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			memberDao.deleteMember(em, userId);
			tx.commit();
			res = true;
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		
		return res;
	}
}
