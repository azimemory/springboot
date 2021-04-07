package com.kh.toy.springdata;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.internal.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.CustomException;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.member.Member;

import net.bytebuddy.implementation.bytecode.Throw;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SpringDataTest {
	
	@Autowired
	private SpringDataRepository repo;
	
	String userId = "test";
	
	@Test
	@Transactional
	public void jpaRepositoryTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("test");
		ids.add("jpa");
		ids.add("boot");
		
		Member member = new Member();
		member.setUserId("jpaRepository");
		member.setEmail("jpa@jpa.com");
		member.setTell("010-0112-0119");
		member.setPassword("123456");
		
		//반환형 void면 에러
		repo.findById("test").orElseThrow(()-> new ToAlertException(ErrorCode.NON_EXIST_USER)); 
		Assert.assertNotNull(repo.findAll());
		Assert.assertNotNull(repo.findAllById(ids));
		Assert.assertNotNull(repo.save(member));
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
