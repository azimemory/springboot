package com.kh.boot.member.model.vo;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="tb_member")
@DynamicInsert
@DynamicUpdate
public class Member{
	@Id
	private String userId;
	private String password;
	private String grade;
	private String tell;
	private String email;
	private Date regDate;
	private Date rentableDate;
	private int isLeave;
	
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public int getIsLeave() {
		return isLeave;
	}
	
	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", grade=" + grade + ", tell=" + tell
				+ ", email=" + email + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave="
				+ isLeave + "]";
	}

	
}
