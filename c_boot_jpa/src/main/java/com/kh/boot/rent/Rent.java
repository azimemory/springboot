package com.kh.boot.rent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.boot.member.Member;

@Entity
@DynamicInsert //insert 쿼리를 생성할 때 값이 null인 필드는 쿼리에서 생략
@DynamicUpdate //update 쿼리를 생성할 때 변경사항이 없는 필드는 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long rmIdx;
	private String title; // 대출 건 이름
	@Column(columnDefinition = "date default sysdate")
	private Date regDate; 
	@Column(columnDefinition = "number default 0")
	private boolean isReturn; //반납여부
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Member member;
	
	// ToMany관계인 필드의 fetch Type의 default는 lazy이다.
	// ToOne 관계인 필드의 fetch Type의 default는 EAGER이다.
	// EAGER : 즉시 로딩
	@OneToMany(mappedBy = "rent", fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<RentBook> rentBooks = new ArrayList<RentBook>(); //ToMany관계일 경우 필드 초기화해두기
	
	public long getRmIdx() {
		return rmIdx;
	}
	public void setRmIdx(long rmIdx) {
		this.rmIdx = rmIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public boolean isReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public List<RentBook> getRentBooks() {
		return rentBooks;
	}
	public void setRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;
	}
	@Override
	public String toString() {
		return "Rent [rmIdx=" + rmIdx + ", title=" + title + ", regDate=" + regDate + ", isReturn=" + isReturn
				+ ", member=" + member + ", rentBooks= " + rentBooks+  "]";
	}
}
