package com.kh.toy.common.util.file;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.kh.toy.common.code.Code;

@Entity
@DynamicInsert
@DynamicUpdate
public class FileEntity {
	
	@Id
	@GenericGenerator(name="uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String fileIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	private String isDel;
	Date regDate;

	public String getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(String fileIdx) {
		this.fileIdx = fileIdx;
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
	
	public String getIsDel() {
		return isDel;
	}
	
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getFullPath() {
		return Code.UPLOAD+savePath;
	}
	
}
