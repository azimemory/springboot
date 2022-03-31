package com.kh.toy.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@ToString(exclude = "files")
public class Board {	
	
	@Id
	@GeneratedValue
	private Long bdIdx;
	
	@Column(columnDefinition = "datetime default now()")
	private LocalDate regDate;
	
	private String title;
	private String content;
	
	@Column(columnDefinition = "int default 0")
	private Boolean isDel;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private Member member;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<FileInfo> files = new ArrayList<FileInfo>();
	
	
	
}






