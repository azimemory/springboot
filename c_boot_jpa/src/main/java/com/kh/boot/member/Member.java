package com.kh.boot.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert //insert 쿼리를 생성할 때 값이 null인 필드는 쿼리에서 생략
@DynamicUpdate //update 쿼리를 생성할 때 변경사항이 없는 필드는 쿼리에서 생략
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Member implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	@Column(columnDefinition = "number default 0")
	private boolean isLeave;
	@Column(columnDefinition = "date default sysdate")
	private Date regDate;
	@Column(columnDefinition = "date default sysdate")
	private Date rentableDate;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public boolean isLeave() {
		return isLeave;
	}
	public void setLeave(boolean isLeave) {
		this.isLeave = isLeave;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getRentableDate() {
		return rentableDate;
	}
	public void setRentableDate(Date rentableDate) {
		this.rentableDate = rentableDate;
	}
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", isLeave=" + isLeave + ", regDate=" + regDate + ", rentableDate=" + rentableDate
				+ "]";
	}
}
