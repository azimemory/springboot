package com.kh.toy.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.kh.toy.common.code.Config;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class FileInfo {
	
	@Id
	@GeneratedValue
	private Long flIdx;
	private String originFileName;
	private String renameFileName;
	private String savePath;
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDate regDate;
	
	@Column(columnDefinition = "int default 0")
	private Boolean isDel;
	
	public String getLink() {
		return Config.DOMAIN.DESC + "/file/" + savePath + renameFileName;
	}
	
	public String getDownloadPath() {
		return Config.UPLOAD_PATH.DESC + savePath + renameFileName;
	}
	
	
	
	

}
