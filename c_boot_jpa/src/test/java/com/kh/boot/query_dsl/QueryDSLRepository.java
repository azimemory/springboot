package com.kh.boot.query_dsl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.boot.member.Member;

public interface QueryDSLRepository 
			extends JpaRepository<Member, String>, QueryDSLRepositoryCustom{
	
}
