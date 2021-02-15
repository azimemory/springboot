package com.kh.bookmanager.book.controller;

import java.util.List;

import com.kh.bookmanager.book.model.service.BookService;
import com.kh.bookmanager.book.model.vo.Book;

public class BookController {
	
	BookService bookService = new BookService();
	
	public List<Book> searchAllBooks(){
		List<Book> bookList = bookService.selectAllBooks();
		return bookList;
	}
	
	public List<Book> searchBooksWithRank(){
		List<Book> bookList = bookService.selectBookOrderByRank();
		
		return bookList;
	}
	
	public Book searchBookByTitle(String title) {
		Book book = bookService.selectBookByTitle(title);
		
		return book;
	}
	
	public boolean registBook(Book book) {
		return bookService.insertBook(book);
	}
	
	public boolean modifyBook(Book book) {
		return bookService.updateBook(book);
	}
	
	public boolean deleteBook(String bkIdx) {
		return bookService.deleteBookByBkIdx(bkIdx);
	}

}
