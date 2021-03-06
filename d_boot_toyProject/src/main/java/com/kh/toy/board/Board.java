package com.kh.toy.board;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.kh.toy.common.util.file.FileEntity;

@Entity
@DynamicInsert
@DynamicUpdate
public class Board {
	@Id
	@GenericGenerator(name="uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String bdIdx;
	@ColumnDefault("'guest'")
	private String userId;
	@ColumnDefault("sysdate")
	private Date regDate;
	private String title;
	private String content;
	@ColumnDefault("0")
	private int isDel;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FileEntity> fileEntities;
	
	public String getBdIdx() {
		return bdIdx;
	}

	public void setBdIdx(String bdIdx) {
		this.bdIdx = bdIdx;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	public int getIsDel() {
		return isDel;
	}
	
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public List<FileEntity> getFileEntities() {
		return fileEntities;
	}

	public void setFileEntities(List<FileEntity> fileEntities) {
		this.fileEntities = fileEntities;
	}

	@Override
	public String toString() {
		return "Board [bdIdx=" + bdIdx + ", userId=" + userId + ", regDate=" + regDate + ", title=" + title
				+ ", content=" + content + ", isDel=" + isDel + ", fileEntities=" + fileEntities + "]";
	}

	
}
