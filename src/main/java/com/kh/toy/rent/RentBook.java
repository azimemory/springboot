package com.kh.toy.rent;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.toy.book.Book;

import java.sql.Date;

@Entity
@DynamicInsert
@DynamicUpdate
public class RentBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long rbIdx;
	@Column(columnDefinition = "number default 0")
	private int extentionCnt;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "date default sysdate+7")
	private Date returnDate;
	@Column(columnDefinition = "varchar2(4 char) default '대출'")
	private String state;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "bkIdx")
	private Book book;

	public RentBook() {
	}

	public Long getRbIdx() {
		return rbIdx;
	}

	public void setRbIdx(Long rbIdx) {
		this.rbIdx = rbIdx;
	}

	public int getExtentionCnt() {
		return extentionCnt;
	}

	public void setExtentionCnt(int extentionCnt) {
		this.extentionCnt = extentionCnt;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}