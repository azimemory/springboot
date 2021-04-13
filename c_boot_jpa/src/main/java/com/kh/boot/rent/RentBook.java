package com.kh.boot.rent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.boot.book.Book;

@Entity
@DynamicInsert //insert 쿼리를 생성할 때 값이 null인 필드는 쿼리에서 생략
@DynamicUpdate //update 쿼리를 생성할 때 변경사항이 없는 필드는 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RentBook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long rbIdx;
	@Column(columnDefinition = "varchar2(4 char) default '대출'")
	private String state; //대출 도서 상태 (대출, 연장, 반납, 연체)
	@Column(columnDefinition = "number default 0")
	private int extensionCnt; //연장횟수
	@Column(columnDefinition = "date default sysdate")
	private Date regDate; //등록일자
	@Column(columnDefinition = "date default sysdate + 7")
	private Date returnDate; //반납일자
	
	@ManyToOne
	@JoinColumn(name="bkIdx")
	private Book book;
	
	//CascadeType
	// PERSIST : PERSIST를 수행할 때 연관엔티티도 함께 수행
	// MERGE : 준영속성태인 엔티티를 MERGE 할 때 연관 엔티티도 함께 MERGE
	// REMOVE : 엔티티를 삭제할 때 연관 엔티티 함께 삭제
	// DETACH : 영속상태인 엔티티를 준영속 상태로 만들 때 연관 앤티티 함께 수행
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="rmIdx")
	private Rent rent;
	
	public long getRbIdx() {
		return rbIdx;
	}
	
	public void setRbIdx(long rbIdx) {
		this.rbIdx = rbIdx;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public int getExtensionCnt() {
		return extensionCnt;
	}
	
	public void setExtensionCnt(int extensionCnt) {
		this.extensionCnt = extensionCnt;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Rent getRent() {
		return rent;
	}
	
	public void changeRent(Rent rent) {
		this.rent = rent;
		rent.getRentBooks().add(this);
	}
	
	@Override
	public String toString() {
		return "RentBook [rbIdx=" + rbIdx + ", state=" + state + ", extensionCnt=" + extensionCnt + ", regDate="
				+ regDate + ", returnDate=" + returnDate + ", book=" + book + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
