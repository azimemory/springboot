package com.kh.bookmanager.rent;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentRepository {
	
	public List<Rent> findRentByUserIdAndState(String userId, Session session) {
		Query<Rent> query 
		= session.createQuery("select distinct r from Rent r inner join fetch r.rentBooks"
				+ " where r.member.userId = :userId"
				+ " and state = '대출'");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	
	public Rent findRentByRbIdx(Long rbIdx, Session session) {
		Query<Rent> query 
		= session.createQuery("from Rent r "
				+ " where rbIdx = :rbIdx");
		query.setParameter("rbIdx", rbIdx);
		return query.getSingleResult();
	}
}
