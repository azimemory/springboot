package com.kh.boot.member.model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MebmerDaoTest {
	
	@Autowired
	MemberDao memberDao;
	
	@DisplayName("회원 조회 되는지 테스트")
	@Test
	void idCheck() {
		
	}
}
