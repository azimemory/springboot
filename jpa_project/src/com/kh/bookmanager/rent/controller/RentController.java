package com.kh.bookmanager.rent.controller;

import java.util.List;

import com.kh.bookmanager.book.model.vo.Book;
import com.kh.bookmanager.rent.model.service.RentService;
import com.kh.bookmanager.rent.model.vo.RentBook;
import com.kh.bookmanager.rent.model.vo.RentMaster;

public class RentController {
	
	RentService rentService = new RentService();
	
	public List<RentMaster> searchRentList(String userId){
		return rentService.selectRentList(userId);		
	}
	
	public List<RentBook> searchRentBookList(String rmIdx){
		return rentService.selectRentBookList(rmIdx);		
	}
	
	public String registRent(List<Book> bookList, String userId) {
		
		RentMaster rent = new RentMaster();
		//대출 건 제목 작성 / 000외 N권
		String title = bookList.get(0).getTitle() + " 외" 
				+ (bookList.size()-1) + "권";
		
		return title;
	}
	
	public boolean returnBook(int rbIdx) {
		return false;
	}
	
	public boolean extendBook(int rbIdx) {
		return false;
	}
}
