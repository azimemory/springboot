package com.kh.bookmanager.book.model.service;

import java.util.ArrayList;
import java.util.List;
import com.kh.bookmanager.book.model.dao.BookDao;
import com.kh.bookmanager.book.model.vo.Book;

public class BookService {
	
	BookDao bookDao = new BookDao();
	public List<Book> selectAllBooks(){
		List<Book> bookList = null;
		
	
		
		return bookList;
	}
	
	public List<Book> selectBookOrderByRank() {
		List<Book> bookList = new ArrayList<Book>();
		
		
		
		return bookList;
	}
	
	public Book selectBookByTitle(String title) {
		Book book = null;
	
		return book;
	}
	
	public int insertBook(Book book) {
		
		
		int res = 0;
		
		
	
		return res;
	}

	public int updateBook(Book book) {
		
		
		int res = 0;
		
		
		
		return res;
	}
	
	public int deleteBookByBIdx(int bIdx){
		
		
		int res = 0;
	
		
		return res;
	}
}
