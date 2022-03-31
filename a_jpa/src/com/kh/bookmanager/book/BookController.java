package com.kh.bookmanager.book;

import java.util.List;

public class BookController {
	
	private BookService bookService =  new BookService();
	
	public List<Book> searchAllBooks(){
		return bookService.searchAllBooks();
	}
	
	public List<Book> searchBookByTitle(String keyword) {
		return bookService.findBookByTitle(keyword);
	}

	public List<Book> searchBookWithRank() {
		return bookService.findBookWithRank();
	}

	public boolean registBook(Book book) {
		return bookService.persistBook(book);
	}

	public boolean modifyBook(long bkIdx, String info) {
		return bookService.modifyBook(bkIdx,info);
	}

	public boolean deleteBook(long bkIdx) {
		return bookService.deleteBook(bkIdx);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
