package com.kh.toy.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersistentLogins {
	@Id
	@Column(columnDefinition = "varchar2(64 char)")
	private String series;
	
	@Column(columnDefinition = "varchar2(64 char) not null")
	private String username;
	
	@Column(columnDefinition = "varchar2(64 char) not null")
	private String token;
	
	@Column(columnDefinition = "timestamp not null")
	private LocalDateTime last_used;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getLast_used() {
		return last_used;
	}

	public void setLast_used(LocalDateTime last_used) {
		this.last_used = last_used;
	}
}