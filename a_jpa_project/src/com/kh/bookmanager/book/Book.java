package com.kh.bookmanager.book;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.sql.Date;

@Entity
@DynamicInsert
@DynamicUpdate
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue
	private Long bkIdx;
	private String author;
	private int bookAmt;
	private String category;
	private String info;
	private String isbn;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private int rentCnt;
	private String title;

	public Book() {
	}

	public Long getBkIdx() {
		return bkIdx;
	}

	public void setBkIdx(Long bkIdx) {
		this.bkIdx = bkIdx;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBookAmt() {
		return bookAmt;
	}

	public void setBookAmt(int bookAmt) {
		this.bookAmt = bookAmt;
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

	public String getTitle() {
		return title;
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