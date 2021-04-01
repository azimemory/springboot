package com.kh.bookmanager.book;

import java.util.List;

public class BookController {
	
	BookService bookService = new BookService();
	
	public List<Book> searchAllBooks(){
		return bookService.findBookAll();
	}
	
	public List<Book> searchBooksWithRank(){
		return bookService.findBookOrderByRank();
	}
	
	public Book searchBookByBkIdx(String bkIdx) {
		return bookService.findBook(Long.parseLong(bkIdx));
	}
	
	public Book searchBookByTitle(String title) {
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
