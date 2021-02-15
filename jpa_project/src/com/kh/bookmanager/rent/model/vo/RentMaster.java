package com.kh.bookmanager.rent.model.vo;

import java.io.Serializable;
import javax.persistence.*;

import com.kh.bookmanager.member.model.vo.Member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RENT_MASTER database table.
 * 
 */
@Entity
@Table(name="RENT_MASTER")
@NamedQuery(name="RentMaster.findAll", query="SELECT r FROM RentMaster r")
public class RentMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RM_IDX")
	private String rmIdx;

	@Column(name="IS_RETURN")
	private BigDecimal isReturn;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Column(name="RENT_BOOK_CNT")
	private BigDecimal rentBookCnt;

	private String title;

	//bi-directional many-to-one association to RentBook
	@OneToMany(mappedBy="rentMaster")
	private List<RentBook> rentBooks;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Member member;

	public RentMaster() {
	}

	public String getRmIdx() {
		return this.rmIdx;
	}

	public void setRmIdx(String rmIdx) {
		this.rmIdx = rmIdx;
	}

	public BigDecimal getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(BigDecimal isReturn) {
		this.isReturn = isReturn;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public BigDecimal getRentBookCnt() {
		return this.rentBookCnt;
	}

	public void setRentBookCnt(BigDecimal rentBookCnt) {
		this.rentBookCnt = rentBookCnt;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<RentBook> getRentBooks() {
		return this.rentBooks;
	}

	public void setRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;
	}

	public RentBook addRentBook(RentBook rentBook) {
		getRentBooks().add(rentBook);
		rentBook.setRentMaster(this);

		return rentBook;
	}

	public RentBook removeRentBook(RentBook rentBook) {
		getRentBooks().remove(rentBook);
		rentBook.setRentMaster(null);

		return rentBook;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}