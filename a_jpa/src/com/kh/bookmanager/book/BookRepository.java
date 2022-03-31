package com.kh.bookmanager.book;

import java.util.List;

import javax.persistence.EntityManager;

public class BookRepository {

	public List<Book> findBookByTitle(EntityManager em, String keyword) {
		
		List<Book> books = em.createQuery("select b from Book b "
				+ "	where b.title like concat('%',:keyword,'%')",Book.class)
				.setParameter("keyword", keyword)
				.getResultList();
		
		return books;
	}

	public List<Book> searchAllBooks(EntityManager em) {
		return em.createQuery("from Book",Book.class).getResultList();
	}

	
	public List<Book> findBookWithRank(EntityManager em) {
		return em.createQuery("select b from Book b order by b.rentCnt desc",Book.class)
				.setMaxResults(3)
				.getResultList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
