package com.kh.bookmanager.book;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class BookRepository {

	public Book findBookByTitle(Session session, String title) {
		Query<Book> query = session.createQuery("select b from Book b "
				+ "where b.title like '%'||:title||'%'",Book.class);
		
		query.setParameter("title", title);
		return query.getSingleResult();
	}
}
