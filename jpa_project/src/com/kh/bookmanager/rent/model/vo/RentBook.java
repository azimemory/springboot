package com.kh.bookmanager.rent.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RENT_BOOK database table.
 * 
 */
@Entity
@Table(name="RENT_BOOK")
@NamedQuery(name="RentBook.findAll", query="SELECT r FROM RentBook r")
public class RentBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RB_IDX")
	private String rbIdx;

	@Column(name="BK_IDX")
	private BigDecimal bkIdx;

	@Column(name="EXTENTION_CNT")
	private BigDecimal extentionCnt;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Temporal(TemporalType.DATE)
	@Column(name="RETURN_DATE")
	private Date returnDate;

	@Column(name="\"STATE\"")
	private String state;

	//bi-directional many-to-one association to RentMaster
	@ManyToOne
	@JoinColumn(name="RM_IDX")
	private RentMaster rentMaster;

	public RentBook() {
	}

	public String getRbIdx() {
		return this.rbIdx;
	}

	public void setRbIdx(String rbIdx) {
		this.rbIdx = rbIdx;
	}

	public BigDecimal getBkIdx() {
		return this.bkIdx;
	}

	public void setBkIdx(BigDecimal bkIdx) {
		this.bkIdx = bkIdx;
	}

	public BigDecimal getExtentionCnt() {
		return this.extentionCnt;
	}

	public void setExtentionCnt(BigDecimal extentionCnt) {
		this.extentionCnt = extentionCnt;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public RentMaster getRentMaster() {
		return this.rentMaster;
	}

	public void setRentMaster(RentMaster rentMaster) {
		this.rentMaster = rentMaster;
	}
}