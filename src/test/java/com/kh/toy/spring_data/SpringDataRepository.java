package com.kh.toy.spring_data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.toy.book.Book;

@Repository
public interface SpringDataRepository extends JpaRepository<Book, Long>{
	
	//도서명이나 작가로 회원 조회
	List<Book> findByTitleOrAuthor(String title, String author);
	
	//카테고리가 문학인 도서의 권수 조회 
	int countByAuthorIs(String author);
	
	//카테고리가 문학이면서 남은 재고가 2권이상이고 제목이 '디'로 시작하는 도서 조회
	List<Book> findByCategoryIsAndBookAmtGreaterThanEqualAndTitleStartingWith(String category, int bookAmt, String title);

}
