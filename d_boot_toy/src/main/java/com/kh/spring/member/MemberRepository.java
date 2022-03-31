package com.kh.spring.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
}
