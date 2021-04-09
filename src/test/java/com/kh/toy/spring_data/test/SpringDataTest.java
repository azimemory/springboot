package com.kh.toy.spring_data.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.toy.book.Book;
import com.kh.toy.spring_data.SpringDataRepository;

@SpringBootTest
public class SpringDataTest {
	
	@Autowired
	private SpringDataRepository repo;
	private Book book;
	
	@BeforeEach
	public void before() {
		book = new Book();
		book.setTitle("백의 그림자");
		book.setCategory("문학");
		book.setAuthor("황정은");
		book.setBookAmt(6);
	}
	
	@Test
	@DisplayName("도서 추가")
	public void save() {
		repo.save(book);
	}
	
	@Test
	@DisplayName("모든 도서 조회")
	public void findAll() {
		repo.findAll();
	}
	
	@Test
	@DisplayName("bkIdx로 도서 조회")
	public void findById() {
		repo.findById(1000L);
	}
	
	@Test
	@DisplayName("bkIdx로 도서 조회")
	public void existsById() {
		repo.existsById(1000L);
	}
	
	@Test
	@DisplayName("도서 권수 ")
	public void count() {
		repo.count();
	}
	
	@Test
	@DisplayName("도서 삭제	")
	public void delete() {
		repo.delete(book);
	}
	
	@Test
	@DisplayName("도서명 또는 작가로 회원 조회	")
	public void findByTitleOrAuthor() {
		repo.findByTitleOrAuthor("비행운","황정은");
	}
	
	@Test
	@DisplayName("카테고리가 문학인 도서의 권수 조회 ")
	public void countByAuthorIs() {
		repo.countByAuthorIs("황정은");
	}
	@Test
	@DisplayName("카테고리가 문학이면서 남은 재고가 2권이상이고 제목이 '디'로 시작하는 도서 조회 ")
	public void findByCategoryIsAndBookAmtGreaterThanEqualAndTitleStartingWith() {
		repo.findByCategoryIsAndBookAmtGreaterThanEqualAndTitleStartingWith("문학",2,"디");
	}

}
