package com.kh.boot.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.boot.member.Member;

public interface QueryDSLRepository 
			extends JpaRepository<Member, String>, QueryDSLRepositoryCustom{
	
}
