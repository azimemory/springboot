package com.kh.bookmanager.member;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Member{
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	
	@Column(columnDefinition = "int default 0")
	private boolean isLeave;
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDateTime regDate;
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDateTime rentableDate;
	
	
}
