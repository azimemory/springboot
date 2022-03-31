package com.kh.bookmanager.book;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@DynamicInsert //insert 쿼리를 생성할 때 값이 null인 필드는 쿼리에서 생략
@DynamicUpdate //update 쿼리를 생성할 때 변경사항이 없는 필드는 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
public class Book {
	
	@Id
	@GeneratedValue //JPA 정책에 따른 식별자 자동 생성
	private Long bkIdx;
	
	private String title;
	private String author;
	private String category;
	private String info;
	private String isbn;
	
	@Column(columnDefinition = "int default 1")
	private int bookAmt;  //도서 재고
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDateTime regDate; //등록 일자
	
	@Column(columnDefinition = "int default 0")
	private int rentCnt;  //대출 횟수


	
	
	
	
	
	
	
	
	
	
	

}
