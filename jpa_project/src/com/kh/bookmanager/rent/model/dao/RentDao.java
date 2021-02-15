package com.kh.bookmanager.rent.model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.kh.bookmanager.rent.model.vo.RentBook;
import com.kh.bookmanager.rent.model.vo.RentMaster;

public class RentDao {
	
	public List<RentMaster> selectRentList(Connection conn, String userId) {
		List<RentMaster> rentList = new ArrayList<RentMaster>();
		return rentList;
	}
	
	public List<RentBook> selectRentBookList(Connection conn, String rmIdx) {
		List<RentBook> rentBookList = new ArrayList<RentBook>();
		return rentBookList;
	}
	
	//tb_rent_master테이블에 주문건 정보 입력
	public int insertRentInfo(Connection conn, RentMaster rent) {
		int result = 0;		
		return result;
	}

	public void insertRentBookInfo(Connection conn, int bIdx)   {		
		
	} 
	
	public void updateReturnRentBook(Connection conn, int rbIdx)  {

	}
	
	public void updateExtendRentState(Connection conn, int rbIdx)  {

	}

}
