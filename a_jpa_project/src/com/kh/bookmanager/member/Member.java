package com.kh.bookmanager.member;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.rent.RentMaster;
import java.util.List;

//2차 캐시 적용
//C:\oraclexe\app\oracle\diag\tnslsnr\DESKTOP-O3BP1VU\listener\trace에서 db 접속로그 확인
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
	private Date regDate;
	@Column(columnDefinition = "date default sysdate")
	private Date rentableDate;
	private String tell;
	
	@OneToMany(fetch = FetchType.EAGER) //즉시 로딩
	//@OneToMany //기본값은 지연로딩
	@JoinColumn(name="userId")
	private List<RentMaster> rentMasters;

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

	public Date getRentableDate() {
		return rentableDate;
	}

	public void setRentableDate(Date rentableDate) {
		this.rentableDate = rentableDate;
	}

	public String getTell() {
		return this.tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public List<RentMaster> getRentMasters() {
		return this.rentMasters;
	}

	public void setRentMasters(List<RentMaster> rentMasters) {
		this.rentMasters = rentMasters;
	}

	public RentMaster addRentMaster(RentMaster rentMaster) {
		getRentMasters().add(rentMaster);
		rentMaster.setMember(this);
		return rentMaster;
	}

	public RentMaster removeRentMaster(RentMaster rentMaster) {
		getRentMasters().remove(rentMaster);
		rentMaster.setMember(null);
		return rentMaster;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", email=" + email + ", grade=" + grade + ", isLeave=" + isLeave
				+ ", password=" + password + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", tell="
				+ tell + ", rentMasters=" + rentMasters + "]";
	}
}