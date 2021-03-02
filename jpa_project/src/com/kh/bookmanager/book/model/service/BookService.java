package com.kh.bookmanager.book.model.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.kh.bookmanager.book.model.dao.BookDao;
import com.kh.bookmanager.book.model.vo.Book;

public class BookService {
	BookDao bookDao = new BookDao();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_project");
	
	public List<Book> selectAllBooks(){
		try(Session session = getSession()){
			return bookDao.selectAllBooks(session);
		}
	}
	
	public List<Book> selectBookOrderByRank() {
		try(Session session = getSession()){
			return bookDao.selectBookOrderByRank(session);
		}
	}
	
	public Book selectBookByTitle(String title) {
		try(Session session = getSession()){
			return bookDao.selectBookByTitle(session, title);
		}
	}
	
	public boolean insertBook(Book book) {
		boolean res = false;
		Transaction tx = null;
		
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			bookDao.insertBook(session, book);
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
			bookDao.updateBook(session, book);
			tx.commit();
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return res;
	}
	
	public boolean deleteBookByBkIdx(String bkIdx){
		boolean res = false;
		Transaction tx = null;
		try(Session session = getSession()){
			tx = session.getTransaction();
			tx.begin();
			bookDao.deleteBookByBkIdx(session, bkIdx);
			tx.commit();
			res = true;
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return res;
	}
	
	private Session getSession() {
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		return session;
	}
}
