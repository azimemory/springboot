package com.kh.bookmanager.rent;

import java.util.List;

import com.kh.bookmanager.book.Book;

public class RentController {
	
	RentService rentService = new RentService();
	
	public List<RentMaster> searchRentList(String userId){
		return null;		
	}
	
	public List<RentBook> searchRentBookList(String rmIdx){
		return null;
	}
	
	public String registRent(List<Book> bookList, String userId) {
		return null;
	}
	
	public boolean returnBook(int rbIdx) {
		return false;
	}
	
	public boolean extendBook(int rbIdx) {
		return false;
	}
}
