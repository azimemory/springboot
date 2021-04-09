package com.kh.bookmanager.rent;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentRepository {
	//N+1문제 : 여러 엔티티를 조회하는 상황 일때 조회되는 엔티티가 참조하고 있는 엔티티를 
	// 찾기 위한 쿼리를 생성하는 과정에서 쿼리가 엔티티의 개수 + 1개만큼 생성되는 상황
	
	//연관관계 매핑이 지정된 엔티티끼리만 fetch join이 가능
	public List<Rent> findAllRent(EntityManager em){
		String jpql = " select distinct r from Rent r "
				+ " left join fetch r.rentBooks rb"
				+ " left join fetch rb.book"
				+ " left join fetch r.member";
		javax.persistence.Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	public List<Rent> findRentsOnRent(String userId, Session session) {
		String jpql = " select distinct r from Rent r "
				+ " inner join fetch r.rentBooks rb"
				+ " where r.member.userId = :userId "
				+ " and rb.state = '대출'";
		
		Query<Rent> query = session.createQuery(jpql,Rent.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
	
}
