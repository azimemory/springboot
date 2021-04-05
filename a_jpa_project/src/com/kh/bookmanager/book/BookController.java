package com.kh.bookmanager.book;

import java.util.List;

public class BookController {
	
	BookService bookService = new BookService();
	
	public List<Book> findBooksAll(){
		return bookService.findBooksAll();
	}
	
	public List<Book> findBooksWithRank(){
		return bookService.findBookOrderByRank();
	}
	
	public Book findBookByBkIdx(String bkIdx) {
		return bookService.findBook(Long.parseLong(bkIdx));
	}
	
	public Book findBookByTitle(String title) {
		return bookService.findBookByTitle(title);
	}
	
	public boolean registBook(Book book) {
		return bookService.persistBook(book);
	}
	
	public boolean modifyBook(Book book) {
		return bookService.updateBook(book);
	}
	
	public boolean deleteBook(String bkIdx) {
		return bookService.deleteBookByBkIdx(Long.parseLong(bkIdx));
	}

}
