package com.kh.bookmanager.book;

import org.hibernate.Session;
import com.kh.bookmanager.jpa.JpaUtil;

public class BookService {
	
	BookRepository bookRepository = new BookRepository();

	public Book findBookByTitle(String title) {
		Book book = null;
		
		try(Session session = JpaUtil.createSession()){
			book = bookRepository.findBookByTitle(session, title);
		}
		
		return book;
	}

	
	
	
	
	
	
	
	
	
	
}
