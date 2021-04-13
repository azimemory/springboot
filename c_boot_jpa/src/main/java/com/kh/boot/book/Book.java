package com.kh.boot.book;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert //insert 쿼리를 생성할 때 값이 null인 필드는 쿼리에서 생략
@DynamicUpdate //update 쿼리를 생성할 때 변경사항이 없는 필드는 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue //JPA 정책에 따른 식별자 자동 생성
	private long bkIdx;
	private String title;
	private String author;
	private String category;
	private String info;
	private String isbn;
	@Column(columnDefinition = "number default 1")
	private int bookAmt;  //도서 재고
	@Column(columnDefinition = "date default sysdate")
	private Date regDate; //등록 일자
	@Column(columnDefinition = "number default 0")
	private int rentCnt;  //대출 횟수
	public long getBkIdx() {
		return bkIdx;
	}
	public void setBkIdx(long bkIdx) {
		this.bkIdx = bkIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getBookAmt() {
		return bookAmt;
	}
	
	public void setBookAmt(int bookAmt) {
		this.bookAmt = bookAmt;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public int getRentCnt() {
		return rentCnt;
	}
	
	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}
	
	@Override
	public String toString() {
		return "Book [bkIdx=" + bkIdx + ", title=" + title + ", author=" + author + ", category=" + category + ", info="
				+ info + ", isbn=" + isbn + ", bookAmt=" + bookAmt + ", regDate=" + regDate + ", rentCnt=" + rentCnt
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
