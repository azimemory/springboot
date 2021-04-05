package com.kh.bookmanager.rent;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.kh.bookmanager.book.Book;
import java.sql.Date;
import java.util.Calendar;

@Entity
@DynamicInsert
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class RentBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long rbIdx;
	@Column(columnDefinition = "number default 0")
	private int extentionCnt;
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Calendar regDate;
	@Column(columnDefinition = "date default sysdate+7")
	@Temporal(TemporalType.DATE)
	private Calendar returnDate;
	@Column(columnDefinition = "varchar2(4 char) default '대출'")
	private String state;

	@OneToOne()
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

	public Calendar getRegDate() {
		return regDate;
	}

	public void setRegDate(Calendar regDate) {
		this.regDate = regDate;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate) {
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

	@Override
	public String toString() {
		return "RentBook [rbIdx=" + rbIdx + ", extentionCnt=" + extentionCnt + ", regDate=" + regDate + ", returnDate="
				+ returnDate + ", state=" + state + ", book=" + book + "]";
	}
}
