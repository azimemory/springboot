package com.kh.bookmanager;

import com.kh.bookmanager.jpa.JpaUtil;
import com.kh.bookmanager.view.Index;

public class Run {

	public static void main(String[] args) {
		JpaUtil util = new JpaUtil(); //EntityManagerFactory를 초기화 하기 위한 코드. 의미없음
		new Index().startMenu();
	}
}
