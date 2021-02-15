package com.kh.bookmanager.rent.model.service;

import java.util.ArrayList;
import java.util.List;

import com.kh.bookmanager.book.model.vo.Book;
import com.kh.bookmanager.rent.model.dao.RentDao;
import com.kh.bookmanager.rent.model.vo.RentBook;
import com.kh.bookmanager.rent.model.vo.RentMaster;

public class RentService {
	
	RentDao rentDao = new RentDao();
	
	public List<RentMaster> selectRentList(String userId){
		List<RentMaster> rentList = new ArrayList<RentMaster>();
		return rentList;
	}
	
	public List<RentBook> selectRentBookList( String rmIdx){
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		return rentBookList;
	}
	
	//도서 대출 처리
	public boolean insertRentInfo(RentMaster rent, List<Book> bookList) {		
		return true;
	}
	
	public boolean updateReturnRentBook(int rbIdx){
		return false;
	}
	
	public boolean updateExtendRentState(int rbIdx){
		return false;
	}
	
}
