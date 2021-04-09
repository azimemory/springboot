package com.kh.toy.query_dsl;

import java.util.List;

import javax.persistence.EntityManager;

import com.kh.toy.member.QMember;
import com.kh.toy.rent.QRent;
import com.kh.toy.rent.Rent;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class QueryDSLRepositoryImpl implements QueryDSLRepositoryCustom{
	
	 private final JPAQueryFactory queryFactory;
	 private QMember m;
	 private QRent r;
	 
	 public QueryDSLRepositoryImpl(EntityManager em) {
		 this.queryFactory = new JPAQueryFactory(em);
	 }

	@Override
	public Rent whereAnd() {
		return null;
	}

	@Override
	public Rent whereOr() {
		return null;
	}

	@Override
	public Rent innerJoin() {
		return null;
	}

	@Override
	public Rent leftJoin() {
		return null;
	}

	@Override
	public List<Rent> ordrBy() {
		return null;
	}

	@Override
	public List<Rent> groupBy() {
		return null;
	}

	@Override
	public List<Rent> subQuery() {
		return null;
	}

	@Override
	public List<Rent> dynamicQuery() {
		return null;
	}
}
