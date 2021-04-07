package com.kh.toy.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kh.toy.member.Member;

public interface SpringDataRepository extends JpaRepository<Member, String>{
	

}
