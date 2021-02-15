package com.kh.bookmanager.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.bookmanager.book.model.vo.Book;

public class BookDao {
	
	public List<Book> selectAllBooks() throws SQLException{		
		Book book = null;
		List<Book> bookList = new ArrayList<Book>();		
				
		return bookList;
	}
	
	public List<Book> selectBookOrderByRank() throws SQLException {		
		Book book = null;
		List<Book> bookList = new ArrayList<Book>();
		
		return bookList;
	}
	
	public Book selectBookByTitle( String title) throws SQLException {
		Book book = null;
		
		return book;
	}
	
	public int insertBook( Book book) throws SQLException {
		int res = 0;
		
		return res;
	}

	public int updateBook( Book book) throws SQLException {
		int res = 0;
			
		return res;
	}
	
	public int deleteBookByBIdx( int bIdx) throws SQLException {
		int res = 0;
		
		return res;
	}
}
