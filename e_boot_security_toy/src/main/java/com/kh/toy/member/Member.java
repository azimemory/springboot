package com.kh.toy.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

//TB_MEMBER 테이블의 ROW 정보를 담을 클래스
@Entity
@DynamicInsert
@DynamicUpdate
public class Member implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String userId;
	private String password;
	private String email;
	
	@Column(columnDefinition = "varchar2(10 char) default '일반'")
	private String grade;
	private String tell;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date rentableDate;
	
	@Column(columnDefinition = "NUMBER default 0")
	private Boolean isLeave;
	
	public Member() {
		
	}

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

	public boolean isLeave() {
		return isLeave;
	}

	public void setLeave(boolean isLeave) {
		this.isLeave = isLeave;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave=" + isLeave
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((isLeave == null) ? 0 : isLeave.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((rentableDate == null) ? 0 : rentableDate.hashCode());
		result = prime * result + ((tell == null) ? 0 : tell.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (isLeave == null) {
			if (other.isLeave != null)
				return false;
		} else if (!isLeave.equals(other.isLeave))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (rentableDate == null) {
			if (other.rentableDate != null)
				return false;
		} else if (!rentableDate.equals(other.rentableDate))
			return false;
		if (tell == null) {
			if (other.tell != null)
				return false;
		} else if (!tell.equals(other.tell))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
