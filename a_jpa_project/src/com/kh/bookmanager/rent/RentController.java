package com.kh.bookmanager.rent;

import java.util.List;

import com.kh.bookmanager.book.Book;

public class RentController {
	
	RentService rentService = new RentService();
	
	public Rent findRent(long rmIdx){
		return rentService.findRent(rmIdx);		
	}
	
	public List<Rent> findRentsOnRent(String userId){
		return rentService.findRentsOnRent(userId);
	}
	
	public boolean registRent(List<Book> bookList, String userId) {
		return rentService.insertRentInfo(bookList, userId);
	}
	
	public boolean returnBook(Rent rent) {
		return rentService.returnRentBook(rent);
	}
}
