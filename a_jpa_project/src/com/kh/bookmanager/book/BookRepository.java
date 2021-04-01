package com.kh.bookmanager.book;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

//JPA raw API를 구현한 Hibernate 클래스 실습
public class BookRepository {
	
	public List<Book> findBookAll(Session session){		
		List<Book> bookList = session.createQuery("from Book").list();
		return bookList;
	}
	
	public List<Book> findBookOrderByRank(Session session) {		
		Query query  = session.createQuery("from Book b order by b.rentCnt");
		query.setMaxResults(2);
		return query.list();
	}
	
	public Book findBookByTitle(Session session,String title) {
		Query query =  session.createQuery("from Book b where b.title like '%'|| :title ||'%'",Book.class);
		query.setParameter("title", title);
		return (Book)query.getSingleResult();
	}
}
