package com.kh.toy.query_dsl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Projection;
import static org.springframework.util.ObjectUtils.isEmpty;
import com.kh.toy.book.Book;
import com.kh.toy.book.QBook;
import com.kh.toy.member.Member;
import com.kh.toy.member.QMember;
import com.kh.toy.rent.QRent;
import com.kh.toy.rent.QRentBook;
import com.kh.toy.rent.Rent;
import com.kh.toy.rent.RentBook;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class QueryDSLRepositoryImpl implements QueryDSLRepositoryCustom{
	
	 private final JPAQueryFactory queryFactory;
	 private QRent r = QRent.rent;
	 private QRentBook rb = QRentBook.rentBook;
	 private QMember m = QMember.member;
	 private QBook b = QBook.book;
	 
	 public QueryDSLRepositoryImpl(EntityManager em) {
		 this.queryFactory = new JPAQueryFactory(em);
	 }

	@Override
	public List<Rent> whereAnd() {
		List<Rent> rents = 
				queryFactory.select(r)
				.from(r)
				.where(r.title.startsWith("디디").and(r.member.userId.eq("test")))
				.fetch();
		return rents;
	}

	@Override
	public List<Rent> whereOr() {
		List<Rent> rents = 
				queryFactory.select(r)
				.from(r)
				.where(r.title.startsWith("디디").or(r.isReturn.eq(true)))
				.fetch();
		return rents;
	}

	@Override
	public List<RentBook> innerJoin() {
		List<RentBook> rentBooks = 
				queryFactory.select(rb)
				.from(rb)
				.innerJoin(rb.rent,r)
				.where(r.member.userId.eq("test"))
				.fetch();
		
		return rentBooks;
	}
	
	@Override
	public List<RentBook> innerJoinProjections() {
		List<RentBook> rentBooks = 
				queryFactory
				.select(Projections.fields(RentBook.class,rb.rbIdx,rb.state))
				.from(rb)
				.innerJoin(rb.rent,r)
				.where(r.member.userId.eq("test"))
				.fetch();
		
		return rentBooks;
	}
	
	@Override
	public List<RentBook> innerJoinTuple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> thetaJoin() {
		List<Member> members = 
				queryFactory
				.selectDistinct(m)
				.from(m,r)
				.where(r.member.userId.eq(m.userId)
					  ,r.isReturn.eq(false))
				.fetch();
		return members;
	}

	@Override
	public List<Book> ordrBy() {
		List<Book> books = 
				queryFactory
				.select(b)
				.from(b)
				.orderBy(b.bookAmt.desc())
				.limit(2)
				.fetch();
		
		return books;
	}

	@Override
	public List<Book> groupBy() {
		List<Book> books = 
				queryFactory
				.select(Projections.fields(Book.class, b.category))
				.from(b)
				.groupBy(b.category)
				.fetch();
		return books;
	}

	@Override
	public List<Member> subQuery() {
		List<Member> members = 
				queryFactory
				.select(m)
				.from(m)
				.where(m.userId.in(
						JPAExpressions.select(r.member.userId)
						.from(rb)
						.innerJoin(rb.rent,r)
						.where(rb.state.eq("대출"))						
					)).fetch();
		return members;
	}

	@Override
	public List<Member> dynamicQuery(String keyword, String tell) {
		List<Member> members = 
			queryFactory
			.selectFrom(m)
			.where(
					emailEqOrUserIdEq(keyword)
					,tellEq(tell))
			.fetch();
		return members;
	}
	
	@Override
	public List<Book> dynamicQuery(Book book) {
		List<Book> books = 
			queryFactory
			.selectFrom(b)
			.where(
					bookAmtGoe(book.getBookAmt())
					,rentCntLoe(book.getRentCnt())
				  )
			.fetch();
		return books;
	}
	
	private BooleanExpression tellEq(String tell) {
	 return isEmpty(tell) ? null : m.tell.eq(tell);
	}
	
	private BooleanExpression emailEqOrUserIdEq(String keyword) {
		 return isEmpty(keyword) ? null : m.userId.eq(keyword).or(m.email.eq(keyword));
	}
	
	private BooleanExpression bookAmtGoe(Integer bookAmtGoe) {
	 return bookAmtGoe == null ? null : b.bookAmt.goe(bookAmtGoe);
	}
	
	private BooleanExpression rentCntLoe(Integer rentCntLoe) {
	 return rentCntLoe == null ? null : b.bookAmt.loe(rentCntLoe);
	}

}
