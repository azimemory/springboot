package com.kh.toy.query_dsl.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.toy.book.Book;
import com.kh.toy.query_dsl.QueryDSLRepository;

@SpringBootTest
public class QueryDslTest {
	
	@Autowired
	QueryDSLRepository repo;
	
	@Test
	@DisplayName("대출건 이름이 디디로 시작, 대출자 test")
	public void whereAnd() {
		System.out.println(repo.whereAnd());
	}
	
	@Test
	@DisplayName("모든 회원 조회")
	public void findAll() {
		repo.findAll();
	}
	
	@Test
	@DisplayName("대출건 이름이 디디로 시작, 또는 대출건 반납완료 ")
	public void whereOr() {
		repo.whereOr();
	}
	
	@Test
	@DisplayName("대출자 이름이 test인 모든 대출도서 목록")
	public void innerJoin() {
		repo.innerJoin();
	}
	
	@Test
	@DisplayName("대출자 이름이 test인 모든 대출도서의 rbIdx와 상태값만 추출 ")
	public void innerJoinProjections() {
		repo.innerJoinProjections();
	}
	
	@Test
	@DisplayName("대출완료 상태인 대출건을 가진 모든 사용자")
	public void thetaJoin() {
		repo.thetaJoin();
	}
	
	@Test
	@DisplayName("도서재고가 가장 많이 남은 도서 두 권")
	public void ordrBy() {
		repo.ordrBy();
	}
	
	@Test
	@DisplayName("카테고리로  groupBy")
	public void groupBy() {
		repo.groupBy();
	}
	
	@Test
	@DisplayName("대출 상태인 대출도서가 존재하는 모든 회원 ")
	public void subQuery() {
		repo.subQuery();
	}
	
	@Test
	@DisplayName("도서재고가 2권 이상 대출 횟수가 1회 이하인 도서")
	public void dynamicQueryWithBook() {
		Book book = new Book();
		book.setBookAmt(2);
		book.setRentCnt(1);
		repo.dynamicQuery(book);
	}
	
	@Test
	@DisplayName("전화번호가 010-0000-1111 이면서 이메일이나 아이디가 test인 회원")
	public void dynamicQuery() {
		repo.dynamicQuery("test","010-0000-1111");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
