package com.kh.boot.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.boot.book.Book;
import com.kh.boot.member.Member;
import com.kh.boot.querydsl.QueryDSLRepository;
import com.kh.boot.rent.Rent;
import com.kh.boot.rent.RentBook;
import com.querydsl.core.Tuple;

@SpringBootTest
public class QueryDSLTest {

	@Autowired
	private QueryDSLRepository repo;
	
	@Test
	@DisplayName("대출건 제목이 '디디'로 시작하고 대출자 id가 test인 대출건")
	public void whereAnd() {
		System.out.println(repo.whereAnd());
	}
	
	@Test
	@DisplayName("대출제목이 '디디'로 시작하거나 대출자가 jpa 인 대출건 모두 조회")
	public void whereOr() {
		for (Rent rent : repo.whereOr()) {
			System.out.println(rent);
		}
	}
	
	@Test
	@DisplayName("whereOr에서 N+1 쿼리가 발생하는 것을 join 해결")
	public void rightJoin() {
		for (Rent rent : repo.rightJoin()) {
			System.out.println(rent);
		}
	}
	
	@Test
	@DisplayName("대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호 조회")
	public void innerJoinProjections() {
		for (Rent rent : repo.innerJoinProjections()) {
			System.out.println(rent);
		}
	}
	
	@Test
	@DisplayName("대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호와 대출자 조회")
	public void innerJoinTuple() {
		for (Tuple tuple : repo.innerJoinTuple()) {
			System.out.println(tuple.get(0, Long.class));
		}
	}
	
	@Test
	@DisplayName("대출도서등록일자와 가입일자가 같은 회원이 존재하는 대출도서")
	public void thetaJoin() {
		for (RentBook rentBook : repo.thetaJoin()) {
			System.out.println(rentBook);
		}
	}
	
	@Test
	@DisplayName("도서 재고 수량을 기준으로 내림차순으로 2권까지 조회")
	public void orderBy() {
		for (Book book : repo.orderBy()) {
			System.out.println(book);
		}
	}
	
	@Test
	@DisplayName("카테고리별 도서들의 최대 재고, 평균 재고, 평균 대출 횟수")
	public void groupBy() {
		for (Tuple tuple : repo.groupBy()) {
			System.out.println(tuple);
		}
	}
	
	@Test
	@DisplayName("대출도서의 상태가 '대출'인 대출도서가 한권이라도 존재하는 회원을 조회")
	public void subQuery() {
		for (Member member : repo.subQuery()) {
			System.out.println(member);
		}
	}
	
	@Test
	@DisplayName("도서 동적쿼리")
	public void dynamicQueryWithBook() {
		Book book = new Book();
		//book.setBookAmt(3);
		//book.setRentCnt(2);
		
		for (Book b : repo.dynamicQuery(book)) {
			System.out.println(b);
		}
	}
	
	@Test
	@DisplayName("회원 동적쿼리")
	public void dynamicQueryWithMember() {
		for (Member member : repo.dynamicQuery(null, "010-0112-0122")) {
			System.out.println(member);
		}
	}
}
