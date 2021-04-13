package com.kh.boot.query_dsl;

import java.util.List;

import com.kh.boot.book.Book;
import com.kh.boot.member.Member;
import com.kh.boot.rent.Rent;
import com.kh.boot.rent.RentBook;
import com.querydsl.core.Tuple;

public interface QueryDSLRepositoryCustom {
	
	//where - and
	List<Rent> whereAnd();
	
	//where - or
	List<Rent> whereOr();
	
	//innerJoin
	List<Rent> rightJoin();
	
	//innerJoin + Projections
	List<Rent> innerJoinProjections();
	
	//innerJoin + tuple
	List<Tuple> innerJoinTuple();
	
	//thetaJoin
	List<RentBook> thetaJoin();
	
	//orderBy
	List<Book> orderBy();
	
	//groupBy
	List<Tuple> groupBy();
	
	//subQuery
	List<Member> subQuery();
	
	//동적쿼리 1
	List<Book> dynamicQuery(Book book);
	
	//동적쿼리 2
	List<Member> dynamicQuery(String keyword, String tell);
	
	
	
	

}
