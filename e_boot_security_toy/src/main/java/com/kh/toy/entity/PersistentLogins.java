package com.kh.toy.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PersistentLogins {
	
	@Column(columnDefinition = "character(64) not null")
	private String username;
	
	@Id
	@Column(columnDefinition = "character(64)")	
	private String series;
	
	@Column(columnDefinition = "character(64) not null")
	private String token;
	
	@Column(columnDefinition = "datetime not null")
	private LocalDateTime lastUsed;
	
	
	
	
	
	
	
	
	
	
}
