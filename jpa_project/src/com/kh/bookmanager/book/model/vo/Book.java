package com.kh.bookmanager.book.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BOOK database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BK_IDX")
	private String bkIdx;

	private String author;

	@Column(name="BOOK_AMT")
	private BigDecimal bookAmt;

	private String category;

	private String info;

	private String isbn;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="RENT_CNT")
	private int rentCnt;

	private String title;

	public Book() {
	}

	public String getBkIdx() {
		return this.bkIdx;
	}

	public void setBkIdx(String bkIdx) {
		this.bkIdx = bkIdx;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getBookAmt() {
		return this.bookAmt;
	}

	public void setBookAmt(BigDecimal bookAmt) {
		this.bookAmt = bookAmt;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getRentCnt() {
		return this.rentCnt;
	}

	public void setRentCnt(int rentCnt) {
		this.rentCnt = rentCnt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [bkIdx=" + bkIdx + ", author=" + author + ", bookAmt=" + bookAmt + ", category=" + category
				+ ", info=" + info + ", isbn=" + isbn + ", regDate=" + regDate + ", rentCnt=" + rentCnt + ", title="
				+ title + "]";
	}

	
	
}