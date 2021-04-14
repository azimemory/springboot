package com.kh.toy.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.kh.toy.common.util.file.FileEntity;
import com.kh.toy.member.Member;

@Entity
@DynamicInsert
@DynamicUpdate
public class Board {
	
	@Id
	@GenericGenerator(name="uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String bdIdx;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	private String title;
	private String content;
	
	@Column(columnDefinition = "number default 0")
	private boolean isDel;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FileEntity> fileEntities = new ArrayList<FileEntity>();
	
	public String getBdIdx() {
		return bdIdx;
	}
	public void setBdIdx(String bdIdx) {
		this.bdIdx = bdIdx;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public List<FileEntity> getFileEntities() {
		return fileEntities;
	}
	public void setFileEntities(List<FileEntity> fileEntities) {
		this.fileEntities = fileEntities;
	}
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	
	
	
}
