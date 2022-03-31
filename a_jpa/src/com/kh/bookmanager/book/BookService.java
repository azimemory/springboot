package com.kh.bookmanager.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.kh.bookmanager.jpa.EntityTemplate;

public class BookService {

	private BookRepository bookRepository = new BookRepository();
	
	public List<Book> findBookByTitle(String keyword) {
		EntityManager em = EntityTemplate.getEntityManager();
		List<Book> books = new ArrayList<Book>();
		
		try {
			books = bookRepository.findBookByTitle(em,keyword);
		} finally {
			em.close();
		}
		
		return books;
	}

	public List<Book> findBookWithRank() {
		
		EntityManager em = EntityTemplate.getEntityManager();
		List<Book> books = new ArrayList<Book>();
		try {
			books = bookRepository.findBookWithRank(em);
		} finally {
			em.close();
		}
		
		return books;
	}

	public List<Book> searchAllBooks() {
		EntityManager em = EntityTemplate.getEntityManager();
		List<Book> books = new ArrayList<Book>();
		
		try {
			books = bookRepository.searchAllBooks(em);
		} finally {
			em.close();
		}
		
		return books;
	}

	public boolean persistBook(Book book) {
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		try {
			em.persist(book);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		return false;
	}

	public boolean modifyBook(long bkIdx, String info) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Book book = em.find(Book.class, bkIdx);
			
			if(book == null) return false;
			
			book.setInfo(info);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		
		
		return false;
	}

	public boolean deleteBook(long bkIdx) {
		
		EntityManager em = EntityTemplate.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			Book book = em.find(Book.class, bkIdx);
			if(book == null) return false;
			em.remove(book);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
