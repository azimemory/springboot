package com.kh.bookmanager.member;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.rent.Rent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//2차 캐시 적용
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY) 
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
	private Calendar regDate;
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Calendar rentableDate;
	private String tell;
	
	//ToMany의 경우 default가 lazy 이기 때문에 npe 방지를 위해 빈 인스턴스를 생성해주는 것이 규약
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member") //즉시 로딩
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Rent> rentMasters = new ArrayList<Rent>();

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

	public Calendar getRegDate() {
		return regDate;
	}

	public void setRegDate(Calendar regDate) {
		this.regDate = regDate;
	}

	public Calendar getRentableDate() {
		return rentableDate;
	}

	public void setRentableDate(Calendar rentableDate) {
		this.rentableDate = rentableDate;
	}

	public String getTell() {
		return this.tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public List<Rent> getRentMasters() {
		return rentMasters;
	}

	public void setRentMasters(List<Rent> rentMasters) {
		this.rentMasters = rentMasters;
	}

	public Rent addRentMaster(Rent rentMaster) {
		getRentMasters().add(rentMaster);
		rentMaster.setMember(this);
		return rentMaster;
	}

	public Rent removeRentMaster(Rent rentMaster) {
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