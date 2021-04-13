package com.kh.boot.query_dsl;

import java.util.List;

import javax.persistence.EntityManager;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.kh.boot.book.Book;
import com.kh.boot.book.QBook;
import com.kh.boot.member.Member;
import com.kh.boot.member.QMember;
import com.kh.boot.rent.QRent;
import com.kh.boot.rent.QRentBook;
import com.kh.boot.rent.Rent;
import com.kh.boot.rent.RentBook;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

//QueryDSLRepository + Impl 형태로 인터페이스명을 작성해야한다.
public class QueryDSLRepositoryImpl implements QueryDSLRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	private QRent rent = QRent.rent;
	private QRentBook rentBook = QRentBook.rentBook;
	private QMember member = QMember.member;
	private QBook book = QBook.book;
		
	public QueryDSLRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<Rent> whereAnd() {
		//대출건 이름이 '디디'로 시작하고 대출자가 test 인 주문건을 조회
		List<Rent> rents =
				queryFactory
				.select(rent)
				.from(rent)
				.where(rent.title.startsWith("디디")
					  ,rent.member.userId.eq("test"))
				.fetch();
		
		return rents;
	}

	@Override
	public List<Rent> whereOr() {
		//대출제목이 '디디'로 시작하거나 대출자가 jpa 인 대출건 모두 조회
		List<Rent> rents = 
				queryFactory
				.selectFrom(rent)
				.where(rent.title.startsWith("디디")
						.or(rent.member.userId.eq("jpa")))
				.fetch();
		return rents;
	}

	//whereOr에서 N+1 쿼리가 발생하는 것을 join 해결
	public List<Rent> rightJoin() {
		//대출제목이 '디디'로 시작하거나 대출자가 jpa 인 대출건 모두 조회
		List<Rent> rents = 
				queryFactory
				.selectFrom(rent)
				.rightJoin(rent.member)
				.fetchJoin()
				.where(rent.title.startsWith("디디")
						.or(rent.member.userId.eq("jpa")))
				.fetch();
		return rents;
	}

	@Override
	public List<Rent> innerJoinProjections() {
		//대출자 아이디가 test인 모든 대출건의 대출건 제목과 대출번호 조회
		//Projections는 타입으로 지정한 엔티티에 존재하는 속성만 조회할 수 있다.
		List<Rent> rents = 
				queryFactory
				.select(Projections.fields(Rent.class, rent.title, rent.rmIdx))
				.from(rent)
				.where(rent.member.userId.eq("test"))
				.fetch();
		return rents;
	}

	@Override
	public List<Tuple> innerJoinTuple() {
		List<Tuple> tuples = 
				queryFactory
				.select(rent.rmIdx, rent.title, rent.member.userId)
				.from(rent)
				.where(rent.member.userId.eq("test"))
				.fetch();
		return tuples;
	}

	@Override
	public List<RentBook> thetaJoin() {
		//연관관계 매핑이 설정되어있지 않아도 조인이 가능
		//대출도서등록일자와 가입일자가 같은 회원이 존재하는 대출도서
		List<RentBook> rentBooks =
				queryFactory
				.selectDistinct(rentBook)
				.from(rentBook,member)
				.where(rentBook.regDate.eq(member.regDate))
				.fetch();
		
		return rentBooks;
	}

	@Override
	public List<Book> orderBy() {
		//도서 재고 수량을 기준으로 내림차순으로 2권까지 조회
		List<Book> books = 
				queryFactory
				.select(book)
				.from(book)
				.orderBy(book.bookAmt.desc())
				.limit(2)
				.fetch();
		return books;
	}

	@Override
	public List<Tuple> groupBy() {
		// 카테고리별 도서들의 최대 재고, 평균 재고, 평균 대출 횟수
		List<Tuple> tuples = 
				queryFactory
				.select(book.category,book.bookAmt.max(), book.bookAmt.avg(), book.rentCnt.avg())
				.from(book)
				.groupBy(book.category)
				.fetch();
		return tuples;
	}

	@Override
	public List<Member> subQuery() {
		//대출도서의 상태가 '대출'인 대출도서가 한권이라도 존재하는 회원을 조회
		List<Member> members = 
				queryFactory
				.select(member)
				.from(member)
				.where(member.userId.in(
						JPAExpressions
						.select(rent.member.userId)
						.from(rent)
						.innerJoin(rent.rentBooks,rentBook)
						.where(rentBook.state.eq("대출"))
						)).fetch();
		return members;
	}

	@Override
	public List<Book> dynamicQuery(Book b) {
		//도서 재고가 매개변수로 전달받은 값보다 크거나 같으면서
		//도서 대출 횟수가 매개변수로 전달받은 값보다 작거나 같은 도서를 조회
		//만약 도서 재고나 도서 대출 횟수가 0이면 조건에서 제외
		List<Book> books = 
				queryFactory.select(book)
				.from(book)
				.where(bookAmtGoe(b.getBookAmt())
					  ,rentCntLoe(b.getRentCnt())
					  )
				.fetch();
		return books;
	}

	@Override
	public List<Member> dynamicQuery(String keyword, String tell) {
		//사용자가 입력한 키워드가 이메일 또는 아이디이면서
		//전화번호가 tell과 같은 회원을 조회
		List<Member> members =
				queryFactory
				.select(member)
				.from(member)
				.where(emailEqOrUserIdEq(keyword)
						,tellEq(tell))
				.fetch();
		return members;
	}
	
	private BooleanExpression bookAmtGoe(int bookAmt) {
		return bookAmt == 0 ? null
							: book.bookAmt.goe(bookAmt);
	}
	
	private BooleanExpression rentCntLoe(int rentCnt) {
		return rentCnt == 0 ? null
							: book.rentCnt.loe(rentCnt);
	}
	
	private BooleanExpression emailEqOrUserIdEq(String keyword){
		return isEmpty(keyword) ? null
				: member.userId.eq(keyword)
							   .or(member.email.eq(keyword));
	}
	
	private BooleanExpression tellEq(String tell) {
		return isEmpty(tell) ? null
							 : member.tell.eq(tell);
	}
	
	
	
	
	
	
	
	
	
}
