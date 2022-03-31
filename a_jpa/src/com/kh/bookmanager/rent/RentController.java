package com.kh.bookmanager.rent;

import java.util.List;

public class RentController {
	
	private RentService rentService = new RentService();
	
	public boolean registRent(String userId, List<Long> bkIdxs) {
		return rentService.persistRentInfo(userId, bkIdxs);
	}

	public boolean returnBook(Long rbIdx) {
		return rentService.returnBook(rbIdx);
	}

	public List<Rent> searchRentList(String userId) {
		return rentService.findRentByUserId(userId);
	}

	public boolean extendsRent(long rbIdx) {
		return rentService.extendsRent(rbIdx);
	}
	
	
	
	
	
	
	
	
	
	
	
}
