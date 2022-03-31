package com.kh.bookmanager.rent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.member.Member;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@DynamicInsert
@DynamicUpdate 
@ToString(exclude = {"rentBooks"})
public class Rent {

	@Id
	@GeneratedValue
	private Long rmIdx;
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDateTime regDate;
	@Column(columnDefinition = "int default 0")
	private Boolean isReturn;
	private String title;
	@Column(columnDefinition = "int default 0")
	private Integer rentBookCnt;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	//CascadeType
	// PERSIST : PERSIST를 수행할 때 연관엔티티도 함께 수행
	// REMOVE : 엔티티를 삭제할 때 연관엔티티도 함께 삭제
	// MERGE : 준영속상태의 엔티티를 MERGE할 때 연관엔티티도 함께 MERGE
	// DETACH : 영속상태의 엔티티를 준영속 상태로 만들 때 연관엔티티도 함께 수행
	// ALL : PERSIST + MERGE + REMOVE + DETACH
	
	//setter 작성 안함
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rent", fetch = FetchType.EAGER)
	private List<RentBook> rentBooks = new ArrayList<RentBook>(); //ToMany 관계일 경우 필드를 초기화 해둘 것
	
	public void changeRentBooks(List<RentBook> rentBooks) {
		this.rentBooks = rentBooks;		
		for (RentBook rentBook : rentBooks) {
			rentBook.setRent(this);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
