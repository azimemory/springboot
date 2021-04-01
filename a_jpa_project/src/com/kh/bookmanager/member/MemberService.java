package com.kh.bookmanager.member;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import oracle.sql.DATE;

public class MemberService {
	//EntityManagerFactory는 threadSafe하기 때문에 전역에 선언
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	MemberRepository memberRepository = new MemberRepository();
	
	public Member findMemberById(String userId){
		//EntityManager는 threadSafe하지 않기 때문에 지역에 선언해준다.
		EntityManager em = emf.createEntityManager();
		Member member = null;
		
		try {
			member = em.find(Member.class, userId);
		} finally {
			em.close();
		}
		return member;
	}
	
	public List<Member> findMemberAll(){
		List<Member> memberList = null;
		EntityManager em = emf.createEntityManager(); // 1. 영속성컨텍스트 생성
		try {
			//fetch-lazy 모드일 경우 rentMaster에 proxy객체가 들어가는 것을 debug로 확인
			//entity-manager가 종료되어 detached 상태가 된 상태에서 rentMaster를 부를 경우
			//먼저 실행 중인 session(transaction)이 없기 때문에 
			//lazy-initializationExcetpion 발생
			memberList = memberRepository.findAll(em); //3. 회원정보 조회
		} finally {
			em.close(); //4. 영속성컨텍스트 종료
		}
		
		return memberList;
	}
	
	public List<Member> findMemberByRegDate(Date begin, Date end){
		List<Member> memberList = null;
		EntityManager em = emf.createEntityManager(); // 1. 영속성컨텍스트 생성
		try {
			memberList = memberRepository.findMemberByRegdate(em, begin, end); //3. 회원정보 조회
		} finally {
			em.close(); //4. 영속성컨텍스트 종료
		}
		
		return memberList;
	}
	
	public boolean persistMember(Member member){
		boolean res = false;
		EntityManager em = emf.createEntityManager(); // 1. 영속성컨텍스트 생성
		EntityTransaction tx = em.getTransaction(); 
		tx.begin(); //2. 트랜잭션 시작
		try {
			em.persist(member); //3. 영속성 등록
			tx.commit(); //4. 쿼리 생성
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); 
		}finally {
			em.close(); //5. 영속성컨텍스트 종료
		}
		return res;
	}
		
	public boolean modifyPassword(Member member){
		boolean res = false;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			//entity manager가 다시 member를 관리하도록 등록
			//반드시 식별자(ID)가 있어야 한다.
			//ex) member.setUserId(null); -> merge에 실패.
			em.merge(member);
			tx.commit();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
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
			em.remove(em.find(Member.class, userId));
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
