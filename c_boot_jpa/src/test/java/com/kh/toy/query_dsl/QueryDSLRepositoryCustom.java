package com.kh.toy.query_dsl;

import java.util.List;

import com.kh.toy.book.Book;
import com.kh.toy.member.Member;
import com.kh.toy.rent.Rent;
import com.kh.toy.rent.RentBook;
import com.querydsl.core.Tuple;

public interface QueryDSLRepositoryCustom {

	//where - and
	List<Rent> whereAnd();
	
	//where - or
	List<Rent> whereOr();
	
	//innerJoin
	List<RentBook> innerJoin();
	
	//innerJoin + Projections로 원하는 컬럼값만 추출 하기
	List<RentBook> innerJoinProjections();
	
	//innerJoin + tuple로 원하는 컬럼값만 추출 하기
	List<Tuple> innerJoinTuple();
	
	//thetaJoin : 연관관계가 없는 두 엔티티를 조인해서 검색
	List<RentBook> thetaJoin();
	
	//orderBy
	List<Book> ordrBy();
	
	//groupBy - 집계함수
	List<Book> groupBy();
	
	//subQuery
	List<Member> subQuery();
	
	//동적쿼리
	List<Book> dynamicQuery(Book book);
	
	//동적쿼리 or
	List<Member> dynamicQuery(String keyword, String tell);
}
