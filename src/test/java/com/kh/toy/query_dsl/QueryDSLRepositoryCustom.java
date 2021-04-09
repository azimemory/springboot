package com.kh.toy.query_dsl;

import java.util.List;

import com.kh.toy.rent.Rent;

public interface QueryDSLRepositoryCustom {

	//where - and
	Rent whereAnd();
	
	//where - or
	Rent whereOr();
	
	//innerJoin
	Rent innerJoin();
	
	//leftJoin-on
	Rent leftJoin();
	
	//orderBy
	List<Rent> ordrBy();
	
	//groupBy - 집계함수
	List<Rent> groupBy();
	
	//subQuery
	List<Rent> subQuery();
	
	//동적쿼리
	List<Rent> dynamicQuery();
}
