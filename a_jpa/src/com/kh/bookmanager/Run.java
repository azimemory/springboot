package com.kh.bookmanager;

import com.kh.bookmanager.jpa.EntityTemplate;
import com.kh.bookmanager.view.Index;

public class Run {

	public static void main(String[] args) {
		EntityTemplate.init();
		new Index().startMenu();
	}
}
