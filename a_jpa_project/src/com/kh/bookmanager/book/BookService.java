package com.kh.bookmanager.book;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookService {
	
	BookRepository bookRepository = new BookRepository();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	
	private Session getSession() {
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		return session;
	}
	
	public Book findBook(Long bkIdx){
		try(Session session = getSession()){
			return session.get(Book.class, bkIdx);
		}
	}
	
	public List<Book> findBooksAll(){
		try(Session session = getSession()){
			return bookRepository.findBooksAll(session);
		}
	}
	
	public List<Book> findBookOrderByRank() {
		try(Session session = getSession()){
			return bookRepository.findBookOrderByRank(session);
		}
	}
	
	public Book findBookByTitle(String title) {
		try(Session session = getSession()){
			return bookRepository.findBookByTitle(session, title);
		}
	}
	
	public boolean persistBook(Book book) {
		boolean res = false;
		Transaction tx = null;
		
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			session.save(book);
			tx.commit();
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return res;
	}

	public boolean updateBook(Book book) {
		boolean res = false;
		Transaction tx = null;
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			Book bookEntity = session.get(Book.class, book.getBkIdx());
			bookEntity.setInfo(book.getInfo());
			tx.commit();
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return res;
	}
	
	public boolean deleteBookByBkIdx(Long bkIdx){
		boolean res = false;
		Transaction tx = null;
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			Book bookEntity = session.get(Book.class,bkIdx);
			session.delete(bookEntity);
			tx.commit();
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return res;
	}
}
