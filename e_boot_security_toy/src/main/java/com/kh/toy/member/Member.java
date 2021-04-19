package com.kh.toy.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@DynamicInsert
@DynamicUpdate
public class Member {
	
	@Id
	private String userId;
	private String password;
	private String email;
	
	@Column(columnDefinition = "varchar2(30 char) default 'ROLE_MEMBER'")
	private String grade;
	private String tell;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date rentableDate;
	
	@Column(columnDefinition = "number default 0")
	private boolean isLeave;
	
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
	public boolean getIsLeave() {
		return isLeave;
	}
	
	public void setIsLeave(boolean isLeave) {
		this.isLeave = isLeave;
	}

	public List<SimpleGrantedAuthority> getAuthority(){
		List<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(grade));
		return auth;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave=" + isLeave
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
	
	

}
