package com.kh.bookmanager.view.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.bookmanager.book.Book;
import com.kh.bookmanager.book.BookController;
import com.kh.bookmanager.rent.Rent;
import com.kh.bookmanager.rent.RentBook;
import com.kh.bookmanager.rent.RentController;

public class RentMenu {
	
	Scanner sc = new Scanner(System.in);
	BookController bookController = new BookController();
	RentController rentController = new RentController();
	
	public void rentMenu() {
		
		do {
			
			System.out.println("\n*** 대출 관리 ***");
			System.out.println("1. 대출 중인 대출건 조회");
			System.out.println("2. 도서 대출");
			System.out.println("3. 도서 반납");
			System.out.println("4. 이전 페이지");
			System.out.print("선택 : ");
			
			switch (sc.nextInt()) {
			case 1: //대출 중인 도서 조회하기
				sc.nextLine();
				System.out.print("대출 중 인 도서를 조회할 사용자의 아이디를 입력하세요 : ");
				for (Rent rent : rentController.findRentsOnRent(sc.nextLine())) {
					System.out.println(rent);
				}
				break;
			case 2: 
				sc.nextLine();
				System.out.print("대출자의 아이디를 입력하세요 : ");
				String userId = sc.nextLine();
				
				List<Book> bookList = new ArrayList<>();
				Book rBook = null;
				
				while(true) {
					System.out.print("대출할 도서의 도서명을 입력하세요 :");
					rBook = bookController.findBookByTitle(sc.nextLine());
					bookList.add(rBook);
					
					System.out.print("대출할 도서가 더 존재하나요?(y/n) : ");
					if(sc.nextLine().toUpperCase().equals("N")) {
						break;
					}
				}
				System.out.println(rentController.registRent(bookList, userId));
				break;
				
			case 3: System.out.print("반납 처리할 대출번호를 입력하세요 : ");
					Rent rent = rentController.findRent(sc.nextLong());
					
					for (int i = 0; i < rent.getRentBooks().size(); i++) {
						System.out.println("[" + i + "] " + rent.getRentBooks().get(i));
					}
					
					System.out.print("반납할 도서의 번호를 입력하세요 :");
					rent.getRentBooks().get(sc.nextInt()).setState("반납");
					if(rentController.returnBook(rent)) {
						System.out.println("반납이 정상처리 되었습니다.");
					}else{
						System.out.println("반납에 실패하였습니다.");
					};
				break;
			case 4: return;
			default:System.out.println("잘못된 숫자를 입력하셨습니다.");
			}
		}while(true);
	}
}
