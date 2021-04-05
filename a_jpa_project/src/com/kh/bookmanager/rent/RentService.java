package com.kh.bookmanager.rent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.member.Member;

public class RentService {
	
	RentRepository rentRepository = new RentRepository();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	
	private Session getSession() {
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		return session;
	}
	
	public Rent findRent(long rmIdx){
		Session session = getSession();
		return session.get(Rent.class, rmIdx);
	}
	
	public List<Rent> findRentsOnRent(String userId){
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		Session session = getSession();
		List<Rent> rents = rentRepository.findRentByUserIdAndState(userId, session);
		return rents;
	}
	
	//도서 대출 처리
	public boolean insertRentInfo(List<Book> bookList, String userId) {	
		Transaction tx = null;
		boolean res = false;
		
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			
			Rent rent = new Rent();
			List<RentBook> rentBooks = new ArrayList<>();
			
			Member memberEntity = session.get(Member.class, userId);
			
			String title = bookList.size() > 1 
					? bookList.get(0).getTitle() + " 외 " + bookList.size() + "권"
					: bookList.get(0).getTitle();
			
			for (Book book : bookList) {
				RentBook rentBook = new RentBook();
				rentBook.setBook(book);
				rentBooks.add(rentBook);
			}
			
			rent.setMember(memberEntity);
			rent.setTitle(title);
			rent.setRentBooks(rentBooks);
			rent.setRentBookCnt(bookList.size());
			
			session.save(rent);
			tx.commit();
			res = true;
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return res;
	}
	
	public boolean returnRentBook(Rent rent){
		Transaction tx = null;
		boolean res = false;
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			session.merge(rent);
			tx.commit();
			res = true;
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return res;
	}
}
