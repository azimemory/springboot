package com.kh.bookmanager.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//DAO : DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 가능하다면 하나의 메서드에 하나의 쿼리만 처리하도록 작성
public class MemberRepository {

	public MemberRepository() {}
	
	public Member selectMemberById(EntityManager em, String userId){
		Member member = em.find(Member.class, userId);
		return member;
	}
 		
	public List<Member> selectMemberList(EntityManager em){	
		String jpql = "from Member";
		//String fetchJoin = "select m from Member m join fetch m.rentMasters";
		return em.createQuery(jpql).getResultList();
	}
	
	public void insertMember(EntityManager em, Member member){
		em.persist(member); //값이 null인 필드값도 추가되어 default 제약조건이 동작하지 않는다.
	}
	
	public void updateMember(EntityManager em, Member member){
		Member entity = em.find(Member.class, member.getUserId());
		entity.setPassword(member.getPassword());
	}
	
	public void deleteMember(EntityManager em, String userId){
		Member entity = em.find(Member.class, userId);
		em.remove(entity);
	}
	
	public List<Member> selectMemberByRegdate(EntityManager em, Date begin, Date end){
		List<Member> memberList = new ArrayList<>();
		Query query = em.createQuery("select m from Member m where m.regDate between :begin and :end");
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		memberList = query.getResultList();
		return memberList;
	}
}
