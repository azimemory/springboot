package com.kh.toy.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

//2차 캐시 적용
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
@Entity
@DynamicInsert
@DynamicUpdate
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userId;
	private String email;
	private String grade;
	@Column(columnDefinition = "number default 0")
	private int isLeave;
	private String password;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	private String tell;

	public Member() {
		
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getIsLeave() {
		return this.isLeave;
	}

	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getTell() {
		return this.tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}
}