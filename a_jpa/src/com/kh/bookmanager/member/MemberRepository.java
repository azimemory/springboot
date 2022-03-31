package com.kh.bookmanager.member;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MemberRepository {
	
	public List<Member> findMemberByRegDate(LocalDateTime begin, LocalDateTime end, EntityManager em){
		String jpql = "select m from Member m where m.regDate between :begin and :end";
		
		TypedQuery<Member> query = em.createQuery(jpql,Member.class);
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		
		return query.getResultList();
	}

	public List<Member> findAllMember(EntityManager em) {
		return em.createQuery("from Member",Member.class).getResultList();
	}
}
