package com.kh.toy.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.toy.member.Member;

@Repository
public interface SpringDataRepository extends JpaRepository<Member, String>{
	Member findByEmailAndIsLeaveNot(String email, boolean isLeave);
}
