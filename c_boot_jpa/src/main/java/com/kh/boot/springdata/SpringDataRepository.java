package com.kh.boot.springdata;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kh.boot.book.Book;

public interface SpringDataRepository extends JpaRepository<Book, Long>{
	
	//도서명이나 작가로 도서 검색
	List<Book> findByTitleOrAuthor(String title, String author);
	
	//카테고리가 문학인 도서의 권수를 조회
	int countByCategory(String category);

	//카테고리가 문학이면서 남은 재고가 2권 이상이고 제목이 '디'로 시작하는 도서 조회
	List<Book> findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith(String category, int bookAmt, String title);
	
	
	
	
	
	
	
	
	
	
	
}
