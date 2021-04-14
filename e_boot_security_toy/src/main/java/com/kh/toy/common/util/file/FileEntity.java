package com.kh.toy.common.util.file;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.kh.toy.common.code.Code;

@Entity
@DynamicInsert
@DynamicUpdate
public class FileEntity {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String fileIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	
	@Column(columnDefinition = "date default sysdate")
	@Temporal(TemporalType.DATE )
	private Date regDate;
	
	@Column(columnDefinition = "number default 0")
	private boolean isDel;
	
	public String getFullPath() {
		return Code.UPLOAD + savePath;
	}
	
	public String getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(String fileIdx) {
		this.fileIdx = fileIdx;
	}

	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public String getOriginFileName() {
		return originFileName;
	}
	
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	
	public String getRenameFileName() {
		return renameFileName;
	}
	
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
