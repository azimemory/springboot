package com.kh.bookmanager.member;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
//JPA raw API 실습
public class MemberRepository {

	public MemberRepository() {}
 		
	public List<Member> findAll(EntityManager em){	
		//String jpql = "from Member";
		String fetchJoin = "select distinct m from Member m left join fetch m.rentMasters";
		return em.createQuery(fetchJoin).getResultList();
	}
	
	public List<Member> findMemberByRegdate(EntityManager em, Calendar begin, Calendar end){	
		String jpql = "from Member m "
				+ " left join fetch m.rentMasters rm "
				+ " where rm.regDate between :begin and :end";
		//String fetchJoin = "select m from Member m join fetch m.rentMasters";
		Query query = em.createQuery(jpql);
		query.setParameter("begin", begin);
		query.setParameter("end", end);
		return query.getResultList();
	}
}
