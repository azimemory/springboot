package com.kh.bookmanager.member;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MemberRepository {
	
	public List<Member> findMemberByRegDate(Date begin, Date end, EntityManager em){
		String jpql = "select m from Member m where m.regDate between :begin and :end";
		
		Query query = em.createQuery(jpql);
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		
		return query.getResultList();
	}

	public List<Member> findAllMember(EntityManager em) {
		return em.createQuery("from Member").getResultList();
	}
}
