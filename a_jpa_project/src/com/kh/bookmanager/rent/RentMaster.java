package com.kh.bookmanager.rent;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.kh.bookmanager.member.Member;
import java.sql.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
public class RentMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long rmIdx;
	private String title;
	private int rentBookCnt;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "number default 0")
	private int isReturn;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "rmIdx")
	private List<RentBook> rentBooks;

	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;

	public RentMaster() {
		
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

	public int getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(int isReturn) {
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