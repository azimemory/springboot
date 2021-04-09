package com.kh.bookmanager.member;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.jpa.JpaUtil;

public class MemberService {
	
	MemberRepository memberRepository = new MemberRepository();
	
	public List<Member> findAllMember(){
		
		List<Member> members = null;
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			members = memberRepository.findAllMember(em);
		} finally {
			em.close();
		}
		
		return members;
	}
	
	public List<Member> findMemberByRegDate(Date begin, Date end){
		List<Member> members = null;
		EntityManager em =  JpaUtil.getEntityManager();
		
		try {
			members = memberRepository.findMemberByRegDate(begin, end, em);
		} finally {
			em.close();
		}
		
		return members;
	}
	
	public boolean deleteMember(String userId) {
		boolean res =false;
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Member memberEntity = em.find(Member.class, userId);
			em.remove(memberEntity);
			
			em.getTransaction().commit();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		
		return res;
	}
	
	public boolean modifyMember(Member member) {
		boolean res = false;
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			em.merge(member); //준영속상태였던 member를 영속상태로 변경
			
			res = true;
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			// TODO: handle exception
		}finally {
			em.close();
		}
		
		return res;
	}
	
	public Member findMemberById(String userId) {
		EntityManager em = JpaUtil.getEntityManager();
		
		Member member = null;
		//읽기 작업은 트랜잭션을 명시적으로 열지 않아도 괜찮다.
		try {
			member = em.find(Member.class, userId);
		} finally {
			em.close();
		}
		
		return member;
	}
	
	public boolean persistMember(Member member) {
		boolean res = false;
		EntityManager em = JpaUtil.getEntityManager();
		
		//읽기 작업을 제외한 모든 작업은 반드시 트랜잭션 안에서 수행되어야 한다. 
		EntityTransaction tx = em.getTransaction();
		tx.begin(); //트랜잭션 시작
		
		try {
			em.persist(member); //영속성 컨텍스트에 엔티티를 등록
			tx.commit();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close(); //트랜잭션이 종료되면 반드시 entityManager도 닫아야 한다.
						//entityManager가 닫혀야 connection을 connection Pool에 반환함
		}
		
		return res;
	}
}
