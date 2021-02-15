package com.kh.bookmanager.member.model.vo;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.bookmanager.rent.model.vo.RentMaster;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEMBER database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
@DynamicInsert
@DynamicUpdate
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	private String email;

	private String grade;

	@Column(name="IS_LEAVE")
	private int isLeave;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="REG_DATE")
	private Date regDate;

	@Temporal(TemporalType.DATE)
	@Column(name="RENTABLE_DATE")
	private Date rentableDate;

	private String tell;
	
	//FetchType.EAGER : 즉시로딩 사용, Member Entity를 꾸리기 위한 모든 정보를 즉시 로딩한다. 기본적으로 지연로딩이 설정되어 있다. 
	//지연로딩을 사용하면 TB_MEMBER 테이블에서 회원정보만 가져온 다음 주문 정보가 필요한 시점에 rentMasters 쿼리를 날린다. 
	@OneToMany(mappedBy="member", fetch = FetchType.EAGER)
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
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRentableDate() {
		return this.rentableDate;
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