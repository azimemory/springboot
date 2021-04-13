package com.kh.boot.thymeleaf;

import java.util.Date;

public class Member {
	
	private String userId;
	private String password;
	private String tell;
	private String grade;
	private int loginCnt;
	private Date regDate;
	
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
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getLoginCnt() {
		return loginCnt;
	}
	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", tell=" + tell + ", grade=" + grade
				+ ", loginCnt=" + loginCnt + ", regDate=" + regDate + "]";
	}
	
	

}
