package com.kh.toy.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.toy.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByUserIdAndIsLeave(String userId, boolean isLeave);
}
