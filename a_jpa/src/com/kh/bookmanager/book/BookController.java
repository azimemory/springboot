package com.kh.bookmanager.book;

import java.util.List;

public class BookController {
   
   BookService bookService = new BookService();
   
   public List<Book> searchAllBooks(){
      return null;
   }
   
   public List<Book> searchBooksWithRank(){
      return null;
   }
   
   public Book searchBookByBkIdx(String bkIdx) {
      return null;
   }
   
   public Book searchBookByTitle(String title) {
      return bookService.findBookByTitle(title);
   }
   
   public boolean registBook(Book book) {
      return false;
   }
   
   public boolean modifyBook(Book book) {
      return false;
   }
   
   public boolean deleteBook(String bkIdx) {
      return false;
   }
}