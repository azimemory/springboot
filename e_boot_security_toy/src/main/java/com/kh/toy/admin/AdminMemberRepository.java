package com.kh.toy.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.toy.entity.Member;

public interface AdminMemberRepository extends JpaRepository<Member, String>{

	List<Member> findByIsLeaveEquals(Boolean isLeave);
	
}
