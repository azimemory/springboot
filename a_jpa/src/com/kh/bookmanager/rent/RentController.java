package com.kh.bookmanager.rent;

import java.util.List;

import com.kh.bookmanager.book.Book;

public class RentController {
	
	RentService rentService = new RentService();
	
	public Rent findRent(long rmIdx){
		return rentService.findRentById(rmIdx);		
	}
	
	public List<Rent> findRentsOnRent(String userId){
		return rentService.findRentsOnRent(userId);
	}
	
	public boolean registRent(List<Book> bookList, String userId) {
		return rentService.insertRent(bookList,userId);
	}
	
	public boolean returnBook(RentBook rentBook) {
		return rentService.returnRentBook(rentBook);
	}
	
	public Rent addRentBookToRent(long rbIdx,long rmIdx) {
		return rentService.addRentBookToRent(rmIdx, rbIdx);
	}

	public List<Rent> findAllRent() {
		return rentService.findAllRent();
	}
	
	
	
	
}
