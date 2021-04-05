package com.kh.bookmanager.rent;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.kh.bookmanager.member.Member;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Rent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long rmIdx;
	private String title;
	private int rentBookCnt;
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Calendar regDate;
	@Column(columnDefinition = "number default 0")
	private int isReturn;
	
	//CascadeType : 연관엔티티 처리 정책
	//PERSIST : persist를 수행할 때 연관 앤티티도 함께 persist를 수행
	//MERGE : 준영속상태인 엔티티를 merge할 때 연관 앤티티도 함께 수행
	//REMOVE : 엔티티를 삭제할 때 연관 앤티티도 함께 삭제
	//DETACH : 영속상태인 엔티티를 준 영속상태로 만들 때 연관 앤티티도 함께 수행
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "rmIdx")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	//ToMany의 경우 default가 lazy 이기 때문에 npe 방지를 위해 빈 인스턴스를 생성해주는 것이 규약
	private List<RentBook> rentBooks = new ArrayList<RentBook>();

	@ManyToOne(fetch = FetchType.EAGER)
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

	public Calendar getRegDate() {
		return regDate;
	}

	public void setRegDate(Calendar regDate) {
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

	//Member와 RentMaster가 양방향 매핑이기 때문에 무한 반복되는 것 확인 후 삭제
	public String toString() {
		return "Rent [rmIdx=" + rmIdx + ", title=" + title + ", rentBookCnt=" + rentBookCnt + ", regDate=" 
				+ regDate.getTime()+ ", isReturn=" + isReturn + "]";
	}
	
	
}