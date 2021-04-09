package com.kh.toy.rent;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.toy.member.Member;

import java.sql.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long rmIdx;
	private String title;
	@Column(columnDefinition = "number default 0")
	private int rentBookCnt;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private boolean isReturn;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rmIdx")
	private List<RentBook> rentBooks;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	@JoinColumn(name="userId")
	private Member member;

	public Rent() {
		
	}

	public Long getRmIdx() {
		return rmIdx;
	}

	public void setRmIdx(Long rmIdx) {
		this.rmIdx = rmIdx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRentBookCnt() {
		return rentBookCnt;
	}

	public void setRentBookCnt(int rentBookCnt) {
		this.rentBookCnt = rentBookCnt;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public List<RentBook> getRentBooks() {
		return rentBooks;
	}

	public void setRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}