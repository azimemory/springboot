package com.kh.bookmanager.rent;

import java.util.List;

import javax.persistence.EntityManager;

public class RentRepository {

	public List<Rent> findRentByUserId(EntityManager em, String userId) {
		//distinct : 같은 속성값이 매핑된 entity를 제거
		return em.createQuery("select distinct r from Rent r "
				+ " inner join fetch r.rentBooks rb"
				+ " inner join fetch rb.book"
				+ " inner join fetch r.member m"
				+ " where m.userId = :userId "
				+ " and r.isReturn = false"
				//+ " and rb.state = '대출'"  //part.2
					, Rent.class)
				.setParameter("userId", userId)
				.getResultList();
	}
	
	
}
