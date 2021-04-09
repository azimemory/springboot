package com.kh.toy.query_dsl;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kh.toy.member.Member;

public interface QueryDSLRepository extends JpaRepository<Member, String>,QueryDSLRepositoryCustom {
	
	

}
