package com.kh.bookmanager.rent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.jpa.JpaUtil;
import com.kh.bookmanager.member.Member;

public class RentService {
	
	private RentRepository rentRepository = new RentRepository();
	
	public Rent addRentBookToRent(long rmIdx, long rbIdx) {
		Rent rent = null;
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			rent = em.find(Rent.class, rmIdx);
			RentBook rentBook = em.find(RentBook.class, rbIdx);
			rentBook.changeRent(rent); //rent가 가지고 있는 rentBooks가 증가
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return rent;
	}
	
	public List<Rent> findAllRent(){
		List<Rent> rents = null;
		EntityManager em = JpaUtil.getEntityManager();
		
		try {
			rents = rentRepository.findAllRent(em);
		} finally {
			em.close();
		}
		
		return rents;
	}
	
	public Rent findRentById(long rmIdx) {
		EntityManager em =  JpaUtil.getEntityManager();
		Rent rent = null;
		
		try {
			rent = em.find(Rent.class, rmIdx);
		} finally{
			em.close();
		}
		
		return rent;
	}

	public List<Rent> findRentsOnRent(String userId) {
		
		List<Rent> rents = null;
		try(Session session = JpaUtil.createSession()) {
			rents = rentRepository.findRentsOnRent(userId,session);
		} 
		
		return rents;
	}

	public boolean insertRent(List<Book> books, String userId) {
		Transaction tx = null;
		boolean res = false;
		
		try(Session session = JpaUtil.createSession()) {
			tx = session.getTransaction();
			tx.begin();
			
			Rent rent = new Rent();
			Member memberEntity = session.get(Member.class, userId);
			
			String title = books.size() > 1
					? books.get(0).getTitle() + " 외 " + (books.size()-1) + "권"
					: books.get(0).getTitle();
			
			Rent rentEntity = new Rent();
			rentEntity.setMember(memberEntity);
			rentEntity.setTitle(title);
			
			for (Book book : books) {
				RentBook rentBook = new RentBook();
				rentBook.setBook(book);
				rentBook.changeRent(rentEntity);
				session.save(rentBook);
			}
			tx.commit();
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return res;
	}

	public boolean returnRentBook(RentBook rentBook) {
		boolean res = false;
		Transaction tx = null;
		
		try (Session session = JpaUtil.createSession()){
			tx = session.getTransaction();
			tx.begin();
			session.update(rentBook);
			tx.commit();
			res = true;
		} catch (Exception e) {
			tx.rollback();
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
