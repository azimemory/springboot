package com.kh.boot.spring_data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.boot.book.Book;

@SpringBootTest
public class SpringDataTest {
	
	@Autowired
	private SpringDataRepository repo;
	private Book book;
	
	@BeforeEach
	public void before() {
		book = new Book();
		book.setTitle("파씨의 입문");
		book.setCategory("문학");
		book.setAuthor("황정은");
		book.setBookAmt(3);
	}
	
	@Test
	@DisplayName("도서 추가")
	public void save() {
		repo.save(book);
	}
	
	@Test
	@DisplayName("도서수정")
	public void saveOrUpdate() {
		book.setBookAmt(19999);
		book.setBkIdx(2);
		repo.save(book);
	}
	
	@Test
	@DisplayName("전체 도서 조회")
	public void findAll() {
		repo.findAll();
	}
	
	@Test
	@DisplayName("bkIdx로 도서 조회")
	public void findById() {
		repo.findById(1L);
	}
	
	@Test
	@DisplayName("전체 도서 수량 체크")
	public void count() {
		repo.count();
	}
	
	
	@Test
	@DisplayName("도서 존재 여부 반환")
	public void existsById() {
		repo.existsById(151L);
	}
	
	@Test
	@DisplayName("도서명과 작가로 도서 검색")
	public void findByTitleOrAuthor() {
		for (Book book : repo.findByTitleOrAuthor("비행운", "황정은")) {
			System.out.println(book);
		}
	}
	
	@Test
	@DisplayName("카테고리가 문학인 도서의 권수")
	public void countByCategory() {
		repo.countByCategory("문학");
	}
	
	@Test
	@DisplayName("망했다.")
	public void findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith() {
		repo.findByCategoryAndBookAmtGreaterThanEqualAndTitleStartingWith("문학", 3, "디");
	}
	
	
	
	
	
	
	

}
