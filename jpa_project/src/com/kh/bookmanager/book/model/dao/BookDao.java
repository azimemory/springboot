package com.kh.bookmanager.book.model.dao;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.kh.bookmanager.book.model.vo.Book;

public class BookDao {
	
	public List<Book> selectAllBooks(Session session){		
		List<Book> bookList = session.createQuery("from Book").list();		
		return bookList;
	}
	
	public List<Book> selectBookOrderByRank(Session session) {		
		Query query  = session.createQuery("from Book b order by b.rentCnt");
		query.setFirstResult(0);
		query.setMaxResults(2);
		return query.list();
	}
	
	public Book selectBookByTitle(Session session,String title) {
		Query query =  session.createQuery("from Book b where b.title = :title",Book.class);
		query.setParameter("title", title);
		return (Book)query.getSingleResult();
	}
	
	public void insertBook(Session session, Book book) {
		session.save(book);
	}

	public void updateBook(Session session, Book book) {
		Book entity = session.get(Book.class, book.getBkIdx());
		session.update(entity);
	}
	
	public void deleteBookByBkIdx(Session session, String bkIdx) {
		Book entity = session.get(Book.class, bkIdx);
		session.delete(entity);
	}
}
