package com.kh.bookmanager.rent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import com.kh.bookmanager.book.Book;

public class RentService {
	
	RentRepository rentRepository = new RentRepository();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	
	private Session getSession() {
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		return session;
	}
	
	public List<RentBook> findRent(String rmIdx){
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		return rentBookList;
	}
	
	//도서 대출 처리
	public boolean insertRentInfo(List<Book> bookList, String userId) {	
		try(Session session = getSession()){
			session.getTransaction().begin();
			
			String title = bookList.get(0).getTitle() + " 외 " + bookList.size() + "권";
			RentMaster rentMaster = new RentMaster();
			rentMaster.setTitle(title);
			for (Book book : bookList) {
				
			}
		}catch(Exception e) {
			
		}finally {
			
		}
		
		return true;
	}
	
	public boolean updateReturnRentBook(int rbIdx){
		return false;
	}
	
	public boolean updateExtendRentState(int rbIdx){
		return false;
	}
	
}
